package com.mini.address.domain.vo;

import lombok.Data;

@Data
public class AddressListVO {

    /**
     * 收货地址id
     */
    private Long id;

    /**
     * 收件人姓名
     */
    private String name;

    /**
     * 收件人电话
     */
    private String tel;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 乡镇
     */
    private String county;

    /**
     * 详细地址
     */
    private String addressDetail;

    /**
     * 默认地址
     */
    private Boolean defaulted;
}
