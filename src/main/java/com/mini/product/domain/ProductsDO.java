package com.mini.product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商品表
 *
 * @TableName products
 */
@TableName(value = "products")
@Data
@ApiModel(value = "ProductsDO对象", description = "商品表")
public class ProductsDO implements Serializable {
    /**
     * 农用物资表id
     */
    @ApiModelProperty("商品id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 关联到categories表
     */
    @ApiModelProperty("关联到categories表")
    @TableField(value = "category_id")
    private Long categoryId;

    /**
     * 物资名称
     */
    @ApiModelProperty("物资名称")
    @TableField(value = "name")
    private String name;

    /**
     * 物资描述
     */
    @ApiModelProperty("物资描述")
    @TableField(value = "description")
    private String description;

    /**
     * 原价
     */
    @ApiModelProperty("原价")
    @TableField(value = "original_price")
    private BigDecimal originalPrice;

    /**
     * 单价
     */
    @ApiModelProperty("单价")
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 库存量
     */
    @ApiModelProperty("库存量")
    @TableField(value = "stock")
    private Integer stock;

    /**
     * 计量单位（如公斤、升、件等）
     */
    @ApiModelProperty("计量单位")
    @TableField(value = "unit")
    private String unit;

    /**
     * 已售数量
     */
    @ApiModelProperty("已售数量")
    @TableField(value = "sold")
    private Integer sold;

    /**
     * 首图链接
     */
    @ApiModelProperty("首图链接")
    @TableField(value = "image_url")
    private String imageUrl;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField(value = "created_at")
    private Date createdAt;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    @TableField(value = "updated_at")
    private Date updatedAt;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 品牌
     */
    @ApiModelProperty("品牌")
    @TableField(value = "brand")
    private String brand;

    /**
     * 产地
     */
    @ApiModelProperty("产地")
    @TableField(value = "origin")
    private String origin;
}