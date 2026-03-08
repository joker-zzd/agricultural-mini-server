package com.mini.promotion.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;

import com.mini.promotion.enums.UserCouponStatus;
import lombok.Data;

/**
 * 用户领取优惠券记录表
 * @TableName user_coupon
 */
@TableName(value ="user_coupon")
@Data
public class UserCoupon {
    /**
     * 用户券id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 优惠券拥有者
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 优惠券模板id
     */
    @TableField(value = "coupon_id")
    private Long couponId;

    /**
     * 优惠券有效期开始时间
     */
    @TableField(value = "term_begin_time")
    private LocalDateTime termBeginTime;

    /**
     * 优惠券有效期结束时间
     */
    @TableField(value = "term_end_time")
    private LocalDateTime termEndTime;

    /**
     * 优惠券使用时间
     */
    @TableField(value = "used_time")
    private LocalDateTime usedTime;

    /**
     * 优惠券状态，1-未使用，2-已使用，3-已失效
     */
    @TableField(value = "status")
    private UserCouponStatus status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}