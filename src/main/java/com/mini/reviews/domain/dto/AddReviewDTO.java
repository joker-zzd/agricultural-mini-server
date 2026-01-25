package com.mini.reviews.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddReviewDTO {
    /**
     * 订单id
     */
    @NotNull(message = "订单id不能为空")
    private Long orderId;

    /**
     * 商品id
     */
    @NotNull(message = "商品id不能为空")
    private Long productId;

    /**
     * 评分（1-5星）
     */
    @NotNull(message = "评分不能为空")
    private Integer rating;

    /**
     * 评价内容
     */
    @NotBlank(message = "评价内容不能为空")
    private String comment;

    /**
     * 用户id
     */
    private Long userId;
}
