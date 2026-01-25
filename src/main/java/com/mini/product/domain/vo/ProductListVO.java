package com.mini.product.domain.vo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductListVO {

    /**
     * 农用物资表id
     */
    private Long id;

    private Long userId;

    /**
     * 关联到categories表
     */
    private Long categoryId;

    /**
     * 物资名称
     */
    private String name;

    /**
     * 物资描述
     */
    private String description;

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 库存量
     */
    private Integer stock;

    /**
     * 计量单位（如公斤、升、件等）
     */
    private String unit;

    /**
     * 已售数量
     */
    private Integer sold;

    /**
     * 首图链接
     */
    private String imageUrl;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private Date createdAt;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 产地
     */
    private String origin;
}
