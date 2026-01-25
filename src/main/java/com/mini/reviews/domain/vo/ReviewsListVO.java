package com.mini.reviews.domain.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewsListVO {

    /**
     * 评价表id
     */
    private Long id;

    /**
     * 评分（1-5星）
     */
    private Integer rating;

    /**
     * 评价内容
     */
    private String comment;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 评论人昵称
     */
    private String nickname;
}
