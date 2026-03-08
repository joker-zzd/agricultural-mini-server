package com.mini.points.service;

import com.mini.points.domain.PointsBoardSeason;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mini.resultvo.ResultVO;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
* @author 19256
* @description 针对表【points_board_season(积分排行榜赛季表)】的数据库操作Service
* @createDate 2026-02-28 01:14:16
*/
public interface PointsBoardSeasonService extends IService<PointsBoardSeason> {
    Long  querySeasonByTime(LocalDateTime time);
}
