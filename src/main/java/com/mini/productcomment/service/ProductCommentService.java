package com.mini.productcomment.service;

import com.mini.common.domain.dto.PageDTO;
import com.mini.productcomment.domain.ProductComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mini.productcomment.domain.dto.ProductCommentDTO;
import com.mini.productcomment.domain.dto.ProductCommentReplyDTO;
import com.mini.productcomment.domain.vo.ProductCommentListVO;
import com.mini.productcomment.query.ProductCommentQuery;
import com.mini.resultvo.ResultVO;

import javax.validation.Valid;

/**
 * @author 19256
 * @description 针对表【product_comment(商品评价与回复表)】的数据库操作Service
 * @createDate 2026-02-19 15:11:36
 */
public interface ProductCommentService extends IService<ProductComment> {
    ResultVO<Void> savaEvaluate(ProductCommentDTO productCommentDTO);

    ResultVO<Void> deleteEvaluate(Long id);

    PageDTO<ProductCommentListVO> findByPage(ProductCommentQuery query);

    ResultVO<Void> addReplyOrAnswer(@Valid ProductCommentReplyDTO productCommentReplyDTO);
}
