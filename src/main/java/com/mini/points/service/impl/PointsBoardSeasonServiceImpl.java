package com.mini.points.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mini.points.domain.PointsBoardSeason;
import com.mini.points.service.PointsBoardSeasonService;
import com.mini.points.mapper.PointsBoardSeasonMapper;
import com.mini.resultvo.ResultVO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author 19256
 * @description 针对表【points_board_season(积分排行榜赛季表)】的数据库操作Service实现
 * @createDate 2026-02-28 01:14:16
 */
@Service
public class PointsBoardSeasonServiceImpl extends ServiceImpl<PointsBoardSeasonMapper, PointsBoardSeason>
        implements PointsBoardSeasonService {

    @Override
    public Long querySeasonByTime(LocalDateTime time) {
        Optional<PointsBoardSeason> optional = this.lambdaQuery()
                .le(PointsBoardSeason::getBeginTime, time)
                .ge(PointsBoardSeason::getEndTime, time)
                .oneOpt();
        return optional.map(PointsBoardSeason::getId).orElse(null);
    }
}




