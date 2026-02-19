package com.mini.productcomment.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@ApiModel("商品评价/回复提交DTO")
public class ProductCommentDTO {
    @ApiModelProperty("商品id")
    @NotNull
    private Long productId;

    @ApiModelProperty("订单id（一级评价必填，回复可不传）")
    private Long orderId;

    @ApiModelProperty("父评论id，0表示一级评价")
    @NotNull
    private Long parentId;

    @ApiModelProperty("被回复的评论id（回复时必填）")
    private Long targetCommentId;

    @ApiModelProperty("评价/回复内容")
    @NotBlank
    @Size(max = 500)
    private String content;

    @ApiModelProperty("评分（1-5，仅一级评价传）")
    @Min(1)
    @Max(5)
    private Integer score;

    @ApiModelProperty("是否匿名")
    private Boolean anonymity;
}
