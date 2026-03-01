package com.mini.promotion.controller;


import com.mini.common.domain.dto.PageDTO;
import com.mini.promotion.domain.dto.CouponFormDTO;
import com.mini.promotion.domain.dto.CouponIssueFormDTO;
import com.mini.promotion.domain.vo.CouponDetailVO;
import com.mini.promotion.domain.vo.CouponPageVO;
import com.mini.promotion.query.CouponQuery;
import com.mini.promotion.service.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupons")
@Api(tags = "优惠券相关接口")
public class CouponController {
    private final CouponService couponService;
    @ApiOperation("新增优惠券接口")
    @PostMapping
    public void saveCoupon(@RequestBody @Valid CouponFormDTO dto){
        couponService.saveCoupon(dto);
    }

    @ApiOperation("修改优惠券接口")
    @PutMapping("{id}")
    public void updateById(@RequestBody @Valid CouponFormDTO dto, @PathVariable("id") Long id){
        couponService.updateById(dto, id);
    }

    @ApiOperation("删除优惠券接口")
    @DeleteMapping("{id}")
    public void deleteById(@ApiParam("优惠券id") @PathVariable("id") Long id){
        couponService.deleteById(id);
    }

    @ApiOperation("查询优惠券详情接口")
    @GetMapping("{id}")
    public CouponDetailVO queryById(@PathVariable @ApiParam("优惠券id") Long id){
        return couponService.queryById(id);
    }

    @ApiOperation("分页查询优惠券列表接口-管理端")
    @GetMapping("/page")
    public PageDTO<CouponPageVO> queryCouponByPage(CouponQuery query){
        return couponService.queryCouponByPage(query);
    }

    @ApiOperation("发放优惠券接口")
    @PutMapping("/{id}/issue")
    public void beginIssue(@RequestBody @Valid CouponIssueFormDTO dto) {
        couponService.beginIssue(dto);
    }

}
