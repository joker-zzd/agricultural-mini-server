package com.mini.points.service.impl;

import com.google.common.collect.Lists;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mini.businessuser.domain.dto.UserDTO;
import com.mini.businessuser.mapper.UserMapper;
import com.mini.common.exception.BadRequestException;
import com.mini.points.constant.RedisConstant;
import com.mini.points.domain.PointsBoard;
import com.mini.points.domain.query.PointsBoardQuery;
import com.mini.points.domain.vo.PointsBoardItemVO;
import com.mini.points.domain.vo.PointsBoardVO;
import com.mini.points.service.PointsBoardService;
import com.mini.points.mapper.PointsBoardMapper;
import com.mini.utils.CollUtils;
import com.mini.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 19256
 * @description 针对表【points_board(赛季积分前100名)】的数据库操作Service实现
 * @createDate 2026-02-28 01:14:11
 */
@Service
@RequiredArgsConstructor
public class PointsBoardServiceImpl extends ServiceImpl<PointsBoardMapper, PointsBoard>
        implements PointsBoardService {
    private final StringRedisTemplate stringRedisTemplate;
    private final UserMapper userMapper;

    @Override
    public PointsBoardVO queryPointsBoardBySeason(PointsBoardQuery query) {
        // 1.获取当前用户
        Long userId = SecurityUtils.getUserId();
        if (userId == null) {
            throw new BadRequestException("用户未登录");
        }
        // 2.是否为历史赛季查询
        Long seasonId = query.getSeason();
        boolean isCurrent = seasonId == null || seasonId == 0;
        // 3.查询本人的赛季排名和分数
        PointsBoard pointsBoard = !isCurrent ? queryMyHistoryPoints(seasonId, userId)
                : queryMyCurrentPoints(userId);
        List<PointsBoard> boardList = null;
        if (!isCurrent) {
            // 4.分页查询历史赛季排行榜，fromDB
            boardList = queryHistoryPoints(seasonId, query.getPageNo(), query.getPageSize());
        } else {
            //5.分页查询本赛季排行榜，fromRedis
            boardList = queryCurrentPoints(query.getPageNo(), query.getPageSize(), false);
        }
        //6.封装List
        //6.1查询赛季排行榜成员名称
        List<Long> uids = boardList.stream().map(PointsBoard::getUserId).toList();
        List<UserDTO> userList = userMapper.selectUserBatchIds(uids);
        Map<Long, String> userMap = userList.stream().collect(Collectors.toMap(UserDTO::getId, UserDTO::getNickname));
        // 6.2将复制 pointsBoard 到 PointsBoardItemVO
        List<PointsBoardItemVO> itemList = boardList.stream().map(board -> {
            PointsBoardItemVO item = new PointsBoardItemVO();
            //设置远程查询名称
            Long userId1 = board.getUserId();
            if (userMap.get(userId1) != null) {
                item.setName(userMap.get(userId1));
            }
            item.setPoints(board.getPoints());
            item.setRank(board.getRank());
            return item;
        }).toList();

        //封装vo
        PointsBoardVO vo = new PointsBoardVO();
        vo.setPoints(pointsBoard.getPoints());
        vo.setRank(pointsBoard.getRank());
        vo.setBoardList(itemList);
        return vo;
    }

    private List<PointsBoard> queryCurrentPoints(
            @Min(value = 1, message = "页码不能小于1") Integer pageNo,
            @Min(value = 1, message = "每页查询数量不能小于1") Integer pageSize, boolean toDB) {
        //1.获取本月时间
        //如果调用此方法是将此赛季持久化到mysql，那么查询的就是上个月的赛季排行榜数据
        LocalDate now = toDB ? LocalDate.now().minusMonths(1) : LocalDate.now();
        String format = now.format(DateTimeFormatter.ofPattern("yyyyMM"));
        // 2.构建key
        String key = RedisConstant.POINTS_BOARD_KEY_PREFIX + format;
        // 3.计算分页start和end值
        int start = (pageNo - 1) * pageSize;
        int end = start + pageSize - 1;
        // 4.从redis中获取分页数据
        Set<ZSetOperations.TypedTuple<String>> tuples = stringRedisTemplate.boundZSetOps(key)
                .reverseRangeWithScores(start, end);
        if (tuples == null) return CollUtils.emptyList();

        // 5.封装list成员
        int rank = start + 1;
        List<PointsBoard> list = new ArrayList<>();
        for (ZSetOperations.TypedTuple<String> tuple : tuples) {
            Double score = tuple.getScore();
            String value = tuple.getValue();
            if (value == null || score == null) {
                rank++;
                continue;
            }
            PointsBoard pointsBoard = new PointsBoard();
            pointsBoard.setPoints(score.intValue());
            pointsBoard.setUserId(Long.valueOf(value));
            pointsBoard.setRank(rank++);
            list.add(pointsBoard);
        }
        return list;
    }

    private List<PointsBoard> queryHistoryPoints(Long seasonId, @Min(value = 1, message = "页码不能小于1") Integer pageNo, @Min(value = 1, message = "每页查询数量不能小于1") Integer pageSize) {
        if (seasonId == null) {
            throw new BadRequestException("查询历史赛季排行榜失败，赛季id不能为空");
        }
        int offset = (pageNo - 1) * pageSize;

        return lambdaQuery()
                .eq(PointsBoard::getSeason, seasonId)
                .orderByDesc(PointsBoard::getPoints)
                .last("LIMIT " + pageSize + "OFFSET" + offset).list();

    }

    /**
     * 查询用户历史该赛季积分和排名，fromDB
     *
     * @param seasonId 赛季id
     * @param userId   用户id
     * @return 用户积分和排名信息
     */
    private PointsBoard queryMyHistoryPoints(Long seasonId, Long userId) {
        if (seasonId == null || userId == null) {
            throw new BadRequestException("参数错误");
        }
        return lambdaQuery()
                .eq(PointsBoard::getUserId, userId)
                .eq(PointsBoard::getSeason, seasonId)
                .one();
    }

    /**
     * 查询用户本赛季当前积分和排名，fromRedis
     *
     * @param userId 用户id
     * @return 用户积分和排名信息
     */
    private PointsBoard queryMyCurrentPoints(Long userId) {
        //获取本月时间
        LocalDate now = LocalDate.now();
        String format = now.format(DateTimeFormatter.ofPattern("yyyyMM"));
        //  构建key
        String key = RedisConstant.POINTS_BOARD_KEY_PREFIX + format;
        // 从redis中查询分数
        BoundZSetOperations<String, String> ops = stringRedisTemplate.boundZSetOps(key);
        Double score = ops.score(userId.toString());
        // 查询排名 注意排名是从0开始递增
        Long rank = stringRedisTemplate.boundZSetOps(key).reverseRank(userId.toString());

        // 封装个人信息
        PointsBoard pointsBoard = new PointsBoard();
        pointsBoard.setPoints(score == null ? 0 : score.intValue());
        pointsBoard.setRank(rank == null ? 0 : rank.intValue() + 1);
        pointsBoard.setUserId(userId);

        return pointsBoard;
    }
}




