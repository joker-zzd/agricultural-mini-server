package com.mini.video.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.mini.video.enums.VideoStatus;
import lombok.Data;

/**
 * 商城视频介绍表
 * @TableName video
 */
@TableName(value ="video")
@Data
public class VideoDO {
    /**
     * 主键，自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 视频标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 视频简介
     */
    @TableField(value = "description")
    private String description;

    /**
     * 视频 COS 链接
     */
    @TableField(value = "url")
    private String url;

    /**
     * 封面图片 COS 链接
     */
    @TableField(value = "cover_image_url")
    private String coverImageUrl;

    /**
     * 是否上线（0：下线，1：上线）
     */
    @TableField(value = "status")
    private VideoStatus status;

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
}