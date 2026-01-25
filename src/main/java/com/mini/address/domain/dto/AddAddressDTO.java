package com.mini.address.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class AddAddressDTO {

    /**
     * 收件人姓名
     */
    @NotBlank(message = "收件人不能为空")
    private String name;

    /**
     * 收件人电话
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "/^1[3-9]\\d{9}$/", message = "手机号格式不正确")
    private String tel;

    /**
     * 省份
     */
    @NotBlank(message = "省份不能为空")
    private String province;

    /**
     * 城市
     */
    @NotBlank(message = "城市不能为空")
    private String city;

    /**
     * 乡镇
     */
    @NotBlank(message = "城区或乡镇不能为空")
    private String county;

    /**
     * 详细地址
     */
    @NotBlank(message = "详细地址不能为空")
    private String addressDetail;

    /**
     * 默认地址
     */
    private Boolean defaulted = false;

}
