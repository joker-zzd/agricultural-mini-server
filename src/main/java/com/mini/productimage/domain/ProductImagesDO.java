package com.mini.productimage.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品图片表
 * @TableName product_images
 */
@TableName(value ="product_images")
@Data
public class ProductImagesDO implements Serializable {
    /**
     * 图片id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品图片
     */
    @TableField(value = "image")
    private String image;

    /**
     * 商品id
     */
    @TableField(value = "product_id")
    private Integer productId;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}