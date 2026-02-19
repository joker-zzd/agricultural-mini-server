package com.mini.productcomment.controller;

import com.mini.common.domain.dto.PageDTO;
import com.mini.productcomment.domain.ProductComment;
import com.mini.productcomment.domain.dto.ProductCommentDTO;
import com.mini.productcomment.domain.vo.ProductCommentListVO;
import com.mini.productcomment.query.ProductCommentQuery;
import com.mini.productcomment.service.ProductCommentService;
import com.mini.resultvo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/productComment")
@CrossOrigin
@Api(tags = "商品评价与回复")
@RequiredArgsConstructor
public class ProductCommentController {
    private final ProductCommentService productCommentService;

    @PostMapping("/save")
    @ApiOperation("保存商品评价")
    public ResultVO<Void> save(@RequestBody @Valid ProductCommentDTO productCommentDTO) {
        return productCommentService.savaEvaluate(productCommentDTO);
    }
    @ApiOperation("删除评价")
    @DeleteMapping("/delete/{id}")
    public ResultVO<Void> delete(@PathVariable Long id) {
        return productCommentService.deleteEvaluate(id);
    }

    @ApiOperation("分页查询评价")
    @GetMapping("/findByPage")
    public PageDTO<ProductCommentListVO> findByPage(ProductCommentQuery query) {
        return productCommentService.findByPage(query);
    }

}
