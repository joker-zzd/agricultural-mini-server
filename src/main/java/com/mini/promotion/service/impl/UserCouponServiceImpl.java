package com.mini.promotion.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mini.promotion.domain.UserCoupon;
import com.mini.promotion.service.UserCouponService;
import com.mini.promotion.mapper.UserCouponMapper;
import org.springframework.stereotype.Service;

/**
* @author 19256
* @description 针对表【user_coupon(用户领取优惠券记录表)】的数据库操作Service实现
* @createDate 2026-02-28 15:39:30
*/
@Service
public class UserCouponServiceImpl extends ServiceImpl<UserCouponMapper, UserCoupon>
    implements UserCouponService{

}




