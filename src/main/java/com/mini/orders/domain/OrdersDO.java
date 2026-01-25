package com.mini.orders.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.mini.orders.constant.OrderStatusEnum;
import lombok.Data;

/**
 * 订单表
 * @TableName orders
 */
@TableName(value ="orders")
@Data
public class OrdersDO implements Serializable {
    /**
     * 订单ID，主键，自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 订单日期，默认为当前时间
     */
    @TableField(value = "order_date")
    private Date orderDate;

    /**
     * 总金额
     */
    @TableField(value = "total_amount")
    private BigDecimal totalAmount;

    /**
     * '待付款', '待发货', '待收货', '已完成', '已取消
     */
    @TableField(value = "status")
    private OrderStatusEnum status;

    /**
     * 支付方式
     */
    @TableField(value = "payment_method")
    private String paymentMethod;

    /**
     * 配送地址
     */
    @TableField(value = "shipping_address")
    private String shippingAddress;

    /**
     * 订单编号
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 创建时间，默认为当前时间
     */
    @TableField(value = "created_at")
    private Date createdAt;

    /**
     * 更新时间，自动更新为当前时间
     */
    @TableField(value = "updated_at")
    private Date updatedAt;

    /**
     * 逻辑删除
     */
    @TableField(value = "deleted")
    private Integer deleted;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}