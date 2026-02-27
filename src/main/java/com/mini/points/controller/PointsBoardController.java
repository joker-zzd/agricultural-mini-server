package com.mini.points.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mini.points.domain.PointsBoardSeason;
import com.mini.points.domain.query.PointsBoardQuery;
import com.mini.points.domain.vo.PointsBoardSeasonVO;
import com.mini.points.domain.vo.PointsBoardVO;
import com.mini.points.service.PointsBoardSeasonService;
import com.mini.points.service.PointsBoardService;
import com.mini.utils.BeanUtils;
import com.mini.utils.CollUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 积分排行榜
 *
 * @author 19256
 * @date 2026/2/28
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
@Api(tags = "积分相关接口")
public class PointsBoardController {
    private final PointsBoardService pointsBoardService;
    private final PointsBoardSeasonService pointsBoardSeasonService;

    @ApiOperation("查询赛季信息列表")
    @GetMapping("/seasons/list")
    public List<PointsBoardSeasonVO> queryPointsBoardSeasons() {
        LambdaQueryWrapper<PointsBoardSeason> wrapper = new LambdaQueryWrapper<>();
        wrapper.lt(PointsBoardSeason::getBeginTime, LocalDateTime.now());
        List<PointsBoardSeason> list = this.pointsBoardSeasonService.list(wrapper);
        if (CollUtils.isEmpty(list)) {
            return CollUtils.emptyList();
        }
        return BeanUtils.copyList(list, PointsBoardSeasonVO.class);
    }

    @GetMapping
    @ApiOperation("查询赛季积分榜")
    public PointsBoardVO queryPointsBoardBySeason(PointsBoardQuery query){
        return pointsBoardService.queryPointsBoardBySeason(query);
    }
}
