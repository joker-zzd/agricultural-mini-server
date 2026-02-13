package com.mini.video.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class VideoVO {
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
     * 创建时间
     */
    @TableField(value = "created_at")
    private Date createdAt;
}
