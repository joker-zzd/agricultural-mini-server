package com.mini.points.service;

import com.mini.points.domain.PointsRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mini.points.domain.vo.PointsStatisticsVO;
import com.mini.points.domain.vo.SignResultVO;

import java.util.List;

/**
* @author 19256
* @description 针对表【points_record(用户积分流水记录表（按月统计/清零）)】的数据库操作Service
* @createDate 2026-02-28 01:14:23
*/
public interface PointsRecordService extends IService<PointsRecord> {
    List<PointsStatisticsVO> queryMyPointsToday();

    SignResultVO addSignRecords();
}
