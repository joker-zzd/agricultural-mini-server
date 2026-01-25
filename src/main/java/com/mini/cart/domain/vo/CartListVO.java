package com.mini.cart.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartListVO {
    /**
     * 购物车项ID
     */
    private Long id;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 是否选中，1表示选中
     */
    private Integer checked;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品图片
     */
    private String imageUrl;

    /**
     * 规格
     */
    private String skuName;

    /**
     * sku价格
     */
    private BigDecimal price;

    /**
     * 商品id
     */
    private Long productId;

}
