package com.mini.product.domain;

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
 * 商品表
 * @TableName products
 */
@TableName(value ="products")
@Data
public class ProductsDO implements Serializable {
    /**
     * 农用物资表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 关联到categories表
     */
    @TableField(value = "category_id")
    private Long categoryId;

    /**
     * 物资名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 物资描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 原价
     */
    @TableField(value = "original_price")
    private BigDecimal originalPrice;

    /**
     * 单价
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 库存量
     */
    @TableField(value = "stock")
    private Integer stock;

    /**
     * 计量单位（如公斤、升、件等）
     */
    @TableField(value = "unit")
    private String unit;

    /**
     * 已售数量
     */
    @TableField(value = "sold")
    private Integer sold;

    /**
     * 首图链接
     */
    @TableField(value = "image_url")
    private String imageUrl;

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

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 品牌
     */
    @TableField(value = "brand")
    private String brand;

    /**
     * 产地
     */
    @TableField(value = "origin")
    private String origin;
}