package com.mini.categories.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 分类表
 * @TableName categories
 */
@TableName(value ="categories")
@Data
public class CategoriesDO implements Serializable {
    /**
     * 类别表id

     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 类别名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 类别描述

     */
    @TableField(value = "description")
    private String description;

    /**
     * 父类ID，用于构建多级分类
     */
    @TableField(value = "parent_id")
    private Integer parentId;

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

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}