package com.mini.promotion.mapper;

import com.mini.promotion.domain.UserCoupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 19256
* @description 针对表【user_coupon(用户领取优惠券记录表)】的数据库操作Mapper
* @createDate 2026-02-28 15:39:30
* @Entity com.mini.promotion.domain.UserCoupon
*/
@Mapper
public interface UserCouponMapper extends BaseMapper<UserCoupon> {

}




