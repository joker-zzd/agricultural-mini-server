package com.mini.video.domain.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class VideoDTO {
    /**
     * 视频标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 视频描述
     */
    @NotBlank(message = "描述不能为空")
    private String description;

    /**
     * 视频文件
     */
    @NotEmpty(message = "视频不能为空")
    private MultipartFile videoFile;

    /**
     * 封面图片
     */
    @NotEmpty(message = "封面不能为空")
    private MultipartFile coverImageFile;
}
