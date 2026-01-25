package com.mini.product.controller;

import com.mini.product.domain.vo.ProductDetailVO;
import com.mini.product.domain.vo.ProductListVO;
import com.mini.product.service.ProductsService;
import com.mini.resultvo.ResultVO;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/product")
public class ProductController {

    private final ProductsService productsService;

    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/findByPage")
    public ResultVO<List<ProductListVO>> findByPage(
            @RequestParam(name = "currentPage", defaultValue = "1", required = false) Integer currentPage,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(name = "nickname", required = false) String name,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(required = false) BigDecimal min,
            @RequestParam(required = false) BigDecimal max
    ) {
        return this.productsService.findByPage(currentPage, pageSize, name, description, min, max);
    }

    @GetMapping("/findByPageAndCategoryId")
    public ResultVO<List<ProductListVO>> findByPageAndCategoryId(
            @RequestParam(name = "currentPage", defaultValue = "1", required = false) Integer currentPage,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(name = "nickname", required = false) String name,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) BigDecimal min,
            @RequestParam(required = false) BigDecimal max
    ) {
        return this.productsService.findByPageAndCategoryId(currentPage, pageSize, name, description, categoryId, min, max);
    }

    @GetMapping("findById")
    public ResultVO<ProductDetailVO> findById(Long id) {
        return productsService.findById(id);
    }

    @PutMapping("/updateBySold")
    public ResultVO<Void> updateBySold(Long id, Integer quantity) {
        return productsService.updateBySold(id, quantity);
    }
}
