package com.mini.productcomment.domain.vo;

import com.mini.businessuser.constant.UserType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel("商品评价分页查询返回VO")
public class ProductCommentListVO {
    @ApiModelProperty("评论id")
    private Long id;

    @ApiModelProperty("父评论id，0表示一级评价")
    private Long parentId;

    @ApiModelProperty("根评论id，一级评价为自身id")
    private Long rootId;

    @ApiModelProperty("评论用户id")
    private Long userId;

    @ApiModelProperty("用户类型：MEMBER-成员，MERCHANT-商家，ADMINISTRATOR-管理员")
    private UserType userType;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("评分（1-5），仅一级评价有")
    private Integer score;

    @ApiModelProperty("回复数量")
    private Integer replyTimes;

    @ApiModelProperty("点赞数量")
    private Integer likedTimes;

    @ApiModelProperty("是否匿名")
    private Boolean anonymity;

    @ApiModelProperty("创建时间")
    private LocalDate createTime;

    @ApiModelProperty("一级评论下的回复列表")
    private List<ProductCommentListVO> replyList = new ArrayList<>();

}
