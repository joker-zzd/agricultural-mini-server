package com.mini.orderdetail.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 订单详情表
 * @TableName order_detail
 */
@TableName(value ="order_detail")
@Data
public class OrderDetailDO implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联到orders表
     */
    @TableField(value = "order_id")
    private Long orderId;

    /**
     * 关联到products表
     */
    @TableField(value = "product_id")
    private Long productId;

    /**
     * 数量
     */
    @TableField(value = "quantity")
    private Integer quantity;

    /**
     *单个商品总价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 商品图
     */
    @TableField(value = "image")
    private String image;

    /**
     * 商品名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 商品描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 创建时间
     */
    @TableField(value = "created_at")
    private Date createdAt;

    /**
     * 更新时间
     */
    @TableField(value = "updated_at")
    private Date updatedAt;

    /**
     * 逻辑删除
     */
    @TableField(value = "deleted")
    private Integer deleted;

    /**
     * 是否评价标识
     */
    @TableField(value = "commented")
    private Integer commented;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}