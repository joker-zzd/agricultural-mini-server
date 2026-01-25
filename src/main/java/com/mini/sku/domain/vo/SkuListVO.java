package com.mini.sku.domain.vo;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SkuListVO {

    /**
     * SKU 主键
     */
    private Long id;

    /**
     * SKU 名称（如10kg装）
     */
    private String skuName;

    /**
     * 该 SKU 独立库存（如果有差异）
     */
    private Integer stock;

    /**
     * 商品SKU单价
     */
    private BigDecimal skuPrice;
}
