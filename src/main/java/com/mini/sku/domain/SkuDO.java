package com.mini.sku.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 商品 SKU 表
 * @TableName sku
 */
@TableName(value ="sku")
@Data
public class SkuDO implements Serializable {
    /**
     * SKU 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 对应的商品 SPU ID
     */
    @TableField(value = "product_id")
    private Long productId;

    /**
     * SKU 名称（如10kg装）
     */
    @TableField(value = "sku_name")
    private String skuName;

    /**
     * 该 SKU 独立库存（如果有差异）
     */
    @TableField(value = "stock")
    private Integer stock;

    /**
     * 商品SKU单价
     */
    @TableField(value = "sku_price")
    private BigDecimal skuPrice;

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

    @Version
    @TableField(value = "version")
    private Integer version;
}