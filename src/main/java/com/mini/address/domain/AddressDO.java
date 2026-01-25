package com.mini.address.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 地址表
 * @TableName address
 */
@TableName(value ="address")
@Data
public class AddressDO implements Serializable {
    /**
     * 收货地址id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 收件人姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 收件人电话
     */
    @TableField(value = "tel")
    private String tel;

    /**
     * 省份
     */
    @TableField(value = "province")
    private String province;

    /**
     * 城市
     */
    @TableField(value = "city")
    private String city;

    /**
     * 乡镇
     */
    @TableField(value = "county")
    private String county;

    /**
     * 详细地址
     */
    @TableField(value = "address_detail")
    private String addressDetail;

    /**
     * 创建时间
     */
    @TableField(value = "create_at")
    private Date createAt;

    /**
     * 修改时间
     */
    @TableField(value = "update_at")
    private Date updateAt;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 默认地址
     */
    @TableField(value = "defaulted")
    private Boolean defaulted;
}