package com.mini.promotion.mapper;

import com.mini.promotion.domain.Coupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 19256
* @description 针对表【coupon(优惠券的规则信息)】的数据库操作Mapper
* @createDate 2026-02-28 15:36:26
* @Entity com.mini.promotion.domain.Coupon
*/
@Mapper
public interface CouponMapper extends BaseMapper<Coupon> {

}




