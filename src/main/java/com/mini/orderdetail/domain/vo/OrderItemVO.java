package com.mini.orderdetail.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemVO {
    private Long id;
    private Long productId;
    private BigDecimal price;
    private String image;
    private Integer quantity;
    private String name;
    private String description;
    private Integer commented;
}
