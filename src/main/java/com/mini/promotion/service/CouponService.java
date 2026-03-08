package com.mini.promotion.service;

import com.mini.common.domain.dto.PageDTO;
import com.mini.promotion.domain.Coupon;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mini.promotion.domain.dto.CouponFormDTO;
import com.mini.promotion.domain.dto.CouponIssueFormDTO;
import com.mini.promotion.domain.vo.CouponDetailVO;
import com.mini.promotion.domain.vo.CouponPageVO;
import com.mini.promotion.query.CouponQuery;

import javax.validation.Valid;

/**
* @author 19256
* @description 针对表【coupon(优惠券的规则信息)】的数据库操作Service
* @createDate 2026-02-28 15:36:26
*/
public interface CouponService extends IService<Coupon> {

    void saveCoupon(@Valid CouponFormDTO dto);

    void updateById(@Valid CouponFormDTO dto, Long id);

    void deleteById(Long id);

    CouponDetailVO queryById(Long id);

    PageDTO<CouponPageVO> queryCouponByPage(CouponQuery query);

    void beginIssue(@Valid CouponIssueFormDTO dto);
}
