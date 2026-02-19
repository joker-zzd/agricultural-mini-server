package com.mini.productcomment.domain.dto;

import com.mini.businessuser.constant.UserType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@ApiModel(description = "商品评论回复DTO")
public class ProductCommentReplyDTO {

    @ApiModelProperty("商品id")
    @NotNull(message = "商品id不能为空")
    private Long productId;

    @ApiModelProperty("根评论id")
    @NotNull(message = "根评论id不能为空")
    private Long rootId;

    @ApiModelProperty("父评论id")
    @NotNull(message = "父评论id不能为空")
    private Long parentId;

    @ApiModelProperty("回复用户id")
    @NotNull(message = "回复用户不能为空")
    private Long userId;

    @ApiModelProperty("用户类型：MEMBER-成员，MERCHANT-商家，ADMINISTRATOR-管理员")
    @NotNull(message = "用户类型不能为空")
    private UserType userType;

    @ApiModelProperty("回复内容")
    @NotBlank(message = "回复内容不能为空")
    @Size(max = 500, message = "回复内容不能超过500字")
    private String content;
}
