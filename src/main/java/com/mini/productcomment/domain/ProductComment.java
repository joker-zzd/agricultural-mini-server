package com.mini.productcomment.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import com.mini.businessuser.constant.UserType;
import com.mini.productcomment.enums.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商品评价与回复表
 *
 * @TableName product_comment
 */
@TableName(value = "product_comment")
@Data
public class ProductComment {
    /**
     * 评论id
     */
    @ApiModelProperty("评论id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品id
     */
    @ApiModelProperty("商品id")
    @TableField(value = "product_id")
    private Long productId;

    /**
     * 订单id（评价时必填，回复可为空）
     */
    @ApiModelProperty("订单id（评价时必填，回复可为空）")
    @TableField(value = "order_id")
    private Long orderId;

    /**
     * 父评论id，0表示一级评价
     */
    @ApiModelProperty("父评论id，0表示一级评价")
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 根评论id，一级评价为自身id
     */
    @ApiModelProperty("根评论id，一级评价为自身id")
    @TableField(value = "root_id")
    private Long rootId;

    /**
     * 评论用户id
     */
    @ApiModelProperty("评论用户id")
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 用户类型：1-普通用户 2-商家 3-管理员
     */
    @ApiModelProperty("用户类型：1-普通用户 2-商家 3-管理员")
    @TableField(value = "user_type")
    private UserType userType;

    /**
     * 评论内容
     */
    @ApiModelProperty("评论内容")
    @TableField(value = "content")
    private String content;

    /**
     * 评分（1-5），仅一级评价有
     */
    @ApiModelProperty("评分（1-5），仅一级评价有")
    @TableField(value = "score")
    private Integer score;

    /**
     * 回复数量
     */
    @ApiModelProperty("回复数量")
    @TableField(value = "reply_times")
    private Integer replyTimes;

    /**
     * 点赞数量
     */
    @ApiModelProperty("点赞数量")
    @TableField(value = "liked_times")
    private Integer likedTimes;

    /**
     * 是否匿名
     */
    @ApiModelProperty("是否匿名")
    @TableField(value = "anonymity")
    private Boolean anonymity;

    /**
     * 是否隐藏
     */
    @ApiModelProperty("是否隐藏")
    @TableField(value = "hidden")
    private Boolean hidden;

    /**
     * 管理员查看状态：0-未查看 1-已查看
     */
    @ApiModelProperty("管理员查看状态：0-未查看 1-已查看")
    @TableField(value = "status")
    private Status status;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    @TableField(value = "update_time")
    private Date updateTime;
}