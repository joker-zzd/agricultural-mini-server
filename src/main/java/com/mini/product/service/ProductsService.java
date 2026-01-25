package com.mini.product.service;

import com.mini.product.domain.ProductsDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mini.product.domain.vo.ProductDetailVO;
import com.mini.product.domain.vo.ProductListVO;
import com.mini.resultvo.ResultVO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @author 19256
 * @description 针对表【products(商品表)】的数据库操作Service
 * @createDate 2025-04-15 22:34:47
 */
public interface ProductsService extends IService<ProductsDO> {

    ResultVO<List<ProductListVO>> findByPage(Integer currenPage, Integer pageSize, String name, String description, BigDecimal min, BigDecimal max);

    ResultVO<List<ProductListVO>> findByPageAndCategoryId(Integer currenPage, Integer pageSize, String name, String description, Long categoryId, BigDecimal min, BigDecimal max);

    ResultVO<ProductDetailVO> findById(@NotEmpty(message = "id不能为空") Long id);

    ResultVO<Void> updateBySold(@NotNull(message = "商品id不能为空") Long id, @NotNull(message = "数量不能为空") Integer quantity);
}
