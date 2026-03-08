package com.mini.points.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mini.common.autoconfigure.mq.RabbitMqHelper;
import com.mini.common.constants.MqConstants;
import com.mini.common.exception.BadRequestException;
import com.mini.points.constant.RedisConstant;
import com.mini.points.domain.PointsRecord;
import com.mini.points.domain.vo.PointsStatisticsVO;
import com.mini.points.domain.vo.SignResultVO;
import com.mini.points.enums.PointsRecordType;
import com.mini.points.mq.SignInMessage;
import com.mini.points.service.PointsRecordService;
import com.mini.points.mapper.PointsRecordMapper;
import com.mini.utils.CollUtils;
import com.mini.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 19256
 * @description 针对表【points_record(用户积分流水记录表（按月统计/清零）)】的数据库操作Service实现
 * @createDate 2026-02-28 01:14:23
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PointsRecordServiceImpl extends ServiceImpl<PointsRecordMapper, PointsRecord>
        implements PointsRecordService {
    private final StringRedisTemplate redisTemplate;
    private final RabbitMqHelper mqHelper;

    @Override
    public List<PointsStatisticsVO> queryMyPointsToday() {
        // 1.获取当前用户
        Long userId = SecurityUtils.getUserId();
        // 2. 获取当天开始时间和结束时间
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = now.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endTime = now.withHour(23).withMinute(59).withSecond(59);

        // 3.查询积分记录
        QueryWrapper<PointsRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_id,type, SUM(points) AS points");
        queryWrapper.eq("user_id", userId);
        queryWrapper.between("create_time", startTime, endTime);
        queryWrapper.groupBy("user_id", "type");
        List<PointsRecord> pointsRecords = list(queryWrapper);

        Map<PointsRecordType, PointsRecord> map = null;
        if (CollUtils.isNotEmpty(pointsRecords)) {
            map = pointsRecords.stream().collect(
                    Collectors.toMap(PointsRecord::getType, v -> v)
            );
        }
        ArrayList<PointsStatisticsVO> list = new ArrayList<>();

        // 4.遍历几种枚举值，组装VO
        for (PointsRecordType value : PointsRecordType.values()) {
            PointsStatisticsVO vo = new PointsStatisticsVO();
            // 5.如果该类型积分有记录，则取出积分值，否则默认0
            if (map != null && map.containsKey(value)) {
                vo.setPoints(map.get(value).getPoints());
            } else {
                vo.setPoints(0);
            }
            vo.setType(value.getDesc());
            vo.setMaxPoints(value.getMaxPoints());
            list.add(vo);
        }
        return list;
    }

    @Override
    public SignResultVO addSignRecords() {
        // 1.获取当前用户
        Long userId = SecurityUtils.getUserId();

        // 2.下单
        // 2.1获取当前日期作为key
        LocalDate now = LocalDate.now();
        String format = now.format(DateTimeFormatter.ofPattern(":yyyyMM"));
        String key = RedisConstant.SIGN_RECORD_KEY_PREFIX + userId + format;
        // 2.2使用bitmap尝试进行签到
        // 得到今日在bitmap的坐标(偏移量)
        int offset = now.getDayOfMonth() - 1;
        Boolean signSuccess = redisTemplate.opsForValue().setBit(key, offset, true);
        log.info("用户{}(id)于{}签到[{}]", userId, format, Boolean.TRUE.equals(signSuccess) ? "失败" : "成功");
        if (Boolean.TRUE.equals(signSuccess)) {
            throw new BadRequestException("今日已签到");
        }

        //获取联系签到天数
        int signDays = getSignedDays(now.getDayOfMonth(), key);
        //4.计算当日连续签到奖励(每七天叠加一次积分，每次10分)
        int signReward = signDays / 7 * 10;
        //5.签到结果
        SignResultVO signResultVO = SignResultVO.builder()
                .signDays(signDays)
                .signPoints(10)
                .rewardPoints(signReward)
                .build();
        // 6.保存积分明细记录，发送MQ
        mqHelper.send(
                MqConstants.Exchange.LEARNING_EXCHANGE,
                MqConstants.Key.SIGN_IN,
                SignInMessage.of(userId, signReward + 1)
        ); // 签到积分是奖励积分+基本得分
        return signResultVO;
    }

    private int getSignedDays(int dayOfMonth, String key) {
        //获取bitMap记录值
        List<Long> list = redisTemplate.opsForValue().bitField(key, BitFieldSubCommands.create()
                .get(BitFieldSubCommands
                        .BitFieldType.unsigned(dayOfMonth)).valueAt(0));
        Long bitMapNum = list.get(0);
        log.info("用户本月BitMap签到记录：{}", bitMapNum);
        int countOfDay = 0;
        //只要当天值等于1就是签过到
        while ((bitMapNum & 1) == 1) {
            // 签到天数加1
            countOfDay++;

            // 签到值右移一位
            bitMapNum = bitMapNum >> 1;
        }
        return countOfDay;
    }
}




