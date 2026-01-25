package com.mini.orderdetail.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
    private String image;
    private String name;
    private String description;
}
