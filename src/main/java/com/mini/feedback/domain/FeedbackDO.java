package com.mini.feedback.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 反馈表

 * @TableName feedback
 */
@TableName(value ="feedback")
@Data
public class FeedbackDO implements Serializable {
    /**
     * 反馈id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 反馈内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 反馈图片
     */
    @TableField(value = "image")
    private String image;

    /**
     * 联系方式
     */
    @TableField(value = "contact_details")
    private String contactDetails;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}