package com.mini.reviews.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 评价表
 * @TableName reviews
 */
@TableName(value ="reviews")
@Data
public class ReviewsDO implements Serializable {
    /**
     * 评价表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联到products表
     */
    @TableField(value = "product_id")
    private Long productId;

    /**
     * 关联到users表
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 评分（1-5星）
     */
    @TableField(value = "rating")
    private Integer rating;

    /**
     * 评价内容
     */
    @TableField(value = "comment")
    private String comment;

    /**
     * 创建时间
     */
    @TableField(value = "created_at")
    private Date createdAt;

    /**
     * 更新时间
     */
    @TableField(value = "updated_at")
    private Date updatedAt;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}