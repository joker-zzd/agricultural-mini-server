package com.mini.businessuser.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.mini.businessuser.constant.Type;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class UserDO implements Serializable {
    /**
     * 用户表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField(value = "type")
    private Type type;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 用户密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 用户昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 用户手机号
     */
    @TableField(value = "telephone")
    private String telephone;

    /**
     * 用户头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 出生日期
     */
    @TableField(value = "birthday")
    private Date birthday;

    /**
     * 性别
     */
    @TableField(value = "sex")
    private String sex;

    /**
     * 创建时间
     */
    @TableField(value = "created_at")
    private Date createdAt;

    /**
     * 修改时间
     */
    @TableField(value = "updated_at")
    private Date updatedAt;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}