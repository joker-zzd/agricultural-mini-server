package com.mini.feedback.domain.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class FeedbackDTO {

    /**
     * 反馈内容
     */
    @NotBlank(message = "内容不能为空")
    private String content;

    /**
     * 反馈图片
     */
    private String image;

    /**
     * 联系方式
     */
    @NotBlank(message = "联系方式不能为空")
    private String contactDetails;
}
