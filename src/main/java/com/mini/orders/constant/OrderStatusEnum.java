package com.mini.orders.constant;


public enum OrderStatusEnum {
    /**
     * 待付款
     */
    WAIT_PAYMENT,

    /**
     * 待发货
     */
    WAIT_DELIVER,

    /**
     * 待收货
     */
    WAIT_RECEIVE,

    /**
     * 已完成
     */
    FINISHED,

    /**
     * 已取消
     */
    CANCELED;
}
