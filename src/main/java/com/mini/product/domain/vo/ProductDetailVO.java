package com.mini.product.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mini.productimage.domain.vo.ProductImageItemVO;
import com.mini.reviews.domain.vo.ReviewsListVO;
import com.mini.sku.domain.vo.SkuListVO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ProductDetailVO {
    /**
     * 农用物资表id
     */
    private Long id;

    private Long userId;

    /**
     * 关联到categories表
     */
    private Long categoryId;

    /**
     * 物资名称
     */
    private String name;

    /**
     * 物资描述
     */
    private String description;

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 库存量
     */
    private Integer stock;

    /**
     * 计量单位（如公斤、升、件等）
     */
    private String unit;

    /**
     * 已售数量
     */
    private Integer sold;

    /**
     * 首图链接
     */
    private String imageUrl;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private Date createdAt;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 产地
     */
    private String origin;

    /**
     * 商品详情图
     */
    private List<ProductImageItemVO> imageList;

    /**
     * 评价查询
     */
    private List<ReviewsListVO> reviewList;

    /**
     * sku
     */
    private List<SkuListVO> skuList;

}
