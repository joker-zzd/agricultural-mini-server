package com.mini.points.service;

import com.mini.points.domain.PointsBoard;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mini.points.domain.query.PointsBoardQuery;
import com.mini.points.domain.vo.PointsBoardVO;
import com.mini.points.domain.vo.PointsStatisticsVO;

import java.util.List;

/**
* @author 19256
* @description 针对表【points_board(赛季积分前100名)】的数据库操作Service
* @createDate 2026-02-28 01:14:11
*/
public interface PointsBoardService extends IService<PointsBoard> {
    PointsBoardVO queryPointsBoardBySeason(PointsBoardQuery  query);
}
