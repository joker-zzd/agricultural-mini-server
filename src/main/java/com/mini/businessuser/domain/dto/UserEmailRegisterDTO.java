package com.mini.businessuser.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserEmailRegisterDTO {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "邮箱不能为空")
    private String email;

    @NotBlank(message = "验证码不能为空")
    private String emailCode;
}
