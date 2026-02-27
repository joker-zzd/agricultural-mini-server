package com.mini.points.controller;

import com.mini.points.domain.vo.PointsStatisticsVO;
import com.mini.points.service.PointsBoardService;
import com.mini.points.service.PointsRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "积分相关接口")
@RequestMapping("/api/points")
public class PointsRecordController {
    private final PointsRecordService pointsRecordService;

    @ApiOperation("查询我的今日积分")
    @GetMapping("/today")
    public List<PointsStatisticsVO> queryMyPointsToday(){
        return pointsRecordService.queryMyPointsToday();
    }
}
