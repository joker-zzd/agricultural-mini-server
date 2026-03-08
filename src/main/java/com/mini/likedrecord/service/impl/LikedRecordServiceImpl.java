package com.mini.likedrecord.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mini.common.autoconfigure.mq.RabbitMqHelper;
import com.mini.common.constants.MqConstants;
import com.mini.likedrecord.constant.RedisConstants;
import com.mini.likedrecord.domain.LikedRecord;
import com.mini.likedrecord.domain.dto.LikeRecordFormDTO;
import com.mini.likedrecord.domain.dto.LikedTimesDTO;
import com.mini.likedrecord.service.LikedRecordService;
import com.mini.likedrecord.mapper.LikedRecordMapper;
import com.mini.utils.CollUtils;
import com.mini.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author 19256
 * @description 针对表【liked_record(点赞记录表)】的数据库操作Service实现
 * @createDate 2026-02-20 00:28:56
 */
@Service
@Slf4j
public class LikedRecordServiceImpl extends ServiceImpl<LikedRecordMapper, LikedRecord>
        implements LikedRecordService {
    private final StringRedisTemplate redisTemplate;
    private final RabbitMqHelper rabbitMqHelper;

    public LikedRecordServiceImpl(StringRedisTemplate redisTemplate, RabbitMqHelper rabbitMqHelper) {
        this.redisTemplate = redisTemplate;
        this.rabbitMqHelper = rabbitMqHelper;
    }

    @Override
    public void toggleLike(LikeRecordFormDTO dto) {

        // 1.获取当前用户id
        Long userId = SecurityUtils.getUserId();
        if (userId == null) {
            return;
        }

        // 2.判断点赞类型并存储到Redis
        boolean flag = dto.getLiked() ? liked(userId, dto) : unliked(userId, dto);
        // 2.1.点赞失败
        if (!flag) {
            return;
        }
        // 3.在Redis点赞记录表中统计该业务id的总点赞数(统计人数)
        String key = RedisConstants.LIKE_BIZ_KEY_PREFIX + dto.getBizId();
        Long totalLikeNum = redisTemplate.boundSetOps(key).size();
        if (totalLikeNum == null) {
            return;
        }
        // 4.把业务点赞数存入Redis点赞统计表(ZSet)
        String bizTypeKey = RedisConstants.BIZ_LIKED_COUNT_KEY_PREFIX + dto.getBizType();
        redisTemplate.boundZSetOps(bizTypeKey).add(dto.getBizId().toString(), totalLikeNum);
    }

    /**
     * 获取当前点过赞的业务id集合
     *
     * @param ids 前端传入回答、笔记、评论的id集合，该id集合是当前页面的
     * @return 当前用户点过赞的业务id集合
     */
    @Override
    public Set<Long> getLikedStatusByBizList(List<Long> ids) {
        if (CollUtils.isEmpty(ids)) {
            return new HashSet<>();
        }

        // 1.获取当前用户id
        Long userId = SecurityUtils.getUserId();
        if (userId == null) {
            return new HashSet<>();
        }

        // 2.查询点赞状态，使用pipelined批量查询(适合短时间内大量数据)
        List<Object> objects = redisTemplate.executePipelined((RedisCallback<Object>)
                connection -> {
                    StringRedisConnection src = (StringRedisConnection) connection;
                    for (Long bizId : ids) {
                        String key = RedisConstants.LIKE_BIZ_KEY_PREFIX + bizId;
                        src.sIsMember(key, userId.toString());
                    }
                    return null;
                });

        // 3.返回结果
        return IntStream.range(0, objects.size())
                .filter(i -> (boolean) objects.get(i))
                .mapToObj(ids::get)
                .collect(Collectors.toSet());
    }

    @Override
    public void readLikedTimesAndSendMessage(String bizType, int maxBizSize) {
        // 1.查询redis中30个业务id
        String typeKey = RedisConstants.BIZ_LIKED_COUNT_KEY_PREFIX + bizType;
        Set<ZSetOperations.TypedTuple<String>> typedTuples =
                redisTemplate.opsForZSet()
                        .rangeWithScores(typeKey, 0, maxBizSize - 1);

        if (typedTuples == null || typedTuples.isEmpty()) {
            return;
        }

        // 2. 手动删除
        for (ZSetOperations.TypedTuple<String> tuple : typedTuples) {
            redisTemplate.opsForZSet().remove(typeKey, tuple.getValue());
        }
        ArrayList<LikedTimesDTO> list = new ArrayList<>(typedTuples.size());

        // 3.将查询的业务id封装到list中
        for (ZSetOperations.TypedTuple<String> tuple : typedTuples) {
            Double score = tuple.getScore();
            String value = tuple.getValue();

            // 如果有错误数据就跳过
            if (score == null || StringUtils.isBlank(value)) {
                continue;
            }
            //封装redis数据为待发送的MQ消息数据
            LikedTimesDTO msg = LikedTimesDTO.of(Long.valueOf(value), score.intValue());
            list.add(msg);
        }
        // 4.发送到MQ
        log.debug("准备批量发送点赞统计消息:{}", list);
        rabbitMqHelper.send(MqConstants.Exchange.LIKE_RECORD_EXCHANGE,
                com.mini.utils.StringUtils.format(MqConstants.Key.LIKED_TIMES_KEY_TEMPLATE, bizType), list);

    }

    private boolean liked(Long userId, LikeRecordFormDTO dto) {
        // 判断该业务id是否已经点过赞，没有则添加点赞
        String key = RedisConstants.LIKE_BIZ_KEY_PREFIX + dto.getBizId();

        Long result = redisTemplate.boundSetOps(key).add(userId.toString()); //已点过赞返回0，成功返回1

        //如果result等于0，说明该业务id已经点过赞
        return result != null && result > 0;
    }

    private boolean unliked(Long userId, LikeRecordFormDTO dto) {
        // 删除点赞记录
        String key = RedisConstants.LIKE_BIZ_KEY_PREFIX + dto.getBizId();
        Long result = redisTemplate.boundSetOps(key).remove(userId.toString());
        //如果result等于0，说明该业务id已经点过赞
        return result != null && result > 0;
    }
}




