package com.mini.orders.domain.vo;

import com.mini.orderdetail.domain.vo.OrderItemVO;
import com.mini.orders.constant.OrderStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderListVO {
    /**
     * 订单ID，主键，自增
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 订单日期，默认为当前时间
     */
    private Date orderDate;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * '待付款', '待发货', '待收货', '已完成', '已取消
     */
    private OrderStatusEnum status;

    /**
     * 支付方式
     */
    private String paymentMethod;

    /**
     * 配送地址
     */
    private String shippingAddress;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 创建时间，默认为当前时间
     */
    private Date createdAt;

    private List<OrderItemVO> orderItem;
}
