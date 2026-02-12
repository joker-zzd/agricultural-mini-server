package com.mini.video.domain.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class VideoDTO {
    /**
     * 视频标题
     */
    private String title;

    /**
     * 视频描述
     */
    private String description;

    /**
     * 视频文件
     */
    private MultipartFile videoFile;

    /**
     * 封面图片
     */
    private MultipartFile  coverImageFile;
}
