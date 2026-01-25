package com.mini.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mini.businessuser.domain.UserDO;
import com.mini.businessuser.mapper.UserMapper;
import com.mini.product.domain.ProductsDO;
import com.mini.product.domain.vo.ProductDetailVO;
import com.mini.product.domain.vo.ProductListVO;
import com.mini.product.service.ProductsService;
import com.mini.product.mapper.ProductsMapper;
import com.mini.productimage.domain.ProductImagesDO;
import com.mini.productimage.domain.vo.ProductImageItemVO;
import com.mini.productimage.mapper.ProductImagesMapper;
import com.mini.resultvo.ResultVO;
import com.mini.reviews.domain.ReviewsDO;
import com.mini.reviews.domain.vo.ReviewsListVO;
import com.mini.reviews.mapper.ReviewsMapper;
import com.mini.sku.domain.SkuDO;
import com.mini.sku.domain.vo.SkuListVO;
import com.mini.sku.mapper.SkuMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 19256
 * @description 针对表【products(商品表)】的数据库操作Service实现
 * @createDate 2025-04-15 22:34:47
 */
@Service
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, ProductsDO>
        implements ProductsService {

    private final ProductsMapper productsMapper;
    private final ProductImagesMapper productImagesMapper;
    private final ReviewsMapper reviewsMapper;
    private final UserMapper userMapper;
    private final SkuMapper skuMapper;

    public ProductsServiceImpl(ProductsMapper productsMapper,
                               ProductImagesMapper productImagesMapper,
                               ReviewsMapper reviewsMapper,
                               UserMapper userMapper,
                               SkuMapper skuMapper) {
        this.productsMapper = productsMapper;
        this.productImagesMapper = productImagesMapper;
        this.reviewsMapper = reviewsMapper;
        this.userMapper = userMapper;
        this.skuMapper = skuMapper;
    }

    @Override
    public ResultVO<List<ProductListVO>> findByPage(Integer currenPage, Integer pageSize, String name, String description, BigDecimal min, BigDecimal max) {
        Page<ProductsDO> page = new Page<>(currenPage, pageSize);

        LambdaQueryWrapper<ProductsDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(ProductsDO::getCreatedAt)
                .like(StringUtils.isNotEmpty(name), ProductsDO::getName, name)
                .like(StringUtils.isNotEmpty(description), ProductsDO::getDescription, description)
                .ge(min != null, ProductsDO::getPrice, min)
                .le(max != null, ProductsDO::getPrice, max);

        this.productsMapper.selectPage(page, wrapper);

        List<ProductsDO> productsDOList = page.getRecords();

        List<ProductListVO> productListVOList = productsDOList.stream().map(productsDO -> {
            ProductListVO vo = new ProductListVO();
            vo.setId(productsDO.getId());
            vo.setUserId(productsDO.getUserId());
            vo.setCategoryId(productsDO.getCategoryId());
            vo.setName(productsDO.getName());
            vo.setDescription(productsDO.getDescription());
            vo.setOriginalPrice(productsDO.getOriginalPrice());
            vo.setPrice(productsDO.getPrice());
            vo.setStock(productsDO.getStock());
            vo.setSold(productsDO.getSold());
            vo.setImageUrl(productsDO.getImageUrl());
            vo.setCreatedAt(productsDO.getCreatedAt());
            vo.setBrand(productsDO.getBrand());
            vo.setOrigin(productsDO.getOrigin());

            vo.setName(productsDO.getName());

            return vo;
        }).toList();

        return ResultVO.success(productListVOList, page.getTotal());
    }

    @Override
    public ResultVO<List<ProductListVO>> findByPageAndCategoryId(
            Integer currenPage,
            Integer pageSize,
            String name,
            String description,
            Long categoryId
            , BigDecimal min, BigDecimal max
    ) {
        Page<ProductsDO> page = new Page<>(currenPage, pageSize);

        LambdaQueryWrapper<ProductsDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(ProductsDO::getCreatedAt)
                .like(StringUtils.isNotEmpty(name), ProductsDO::getName, name)
                .like(StringUtils.isNotEmpty(description), ProductsDO::getDescription, description)
                .eq(categoryId != null, ProductsDO::getCategoryId, categoryId)
                .ge(min != null, ProductsDO::getPrice, min)
                .le(max != null, ProductsDO::getPrice, max);


        this.productsMapper.selectPage(page, wrapper);

        List<ProductsDO> productsDOList = page.getRecords();

        List<ProductListVO> productListVOList = productsDOList.stream().map(productsDO -> {
            ProductListVO vo = new ProductListVO();
            vo.setId(productsDO.getId());
            vo.setUserId(productsDO.getUserId());
            vo.setCategoryId(productsDO.getCategoryId());
            vo.setName(productsDO.getName());
            vo.setDescription(productsDO.getDescription());
            vo.setOriginalPrice(productsDO.getOriginalPrice());
            vo.setPrice(productsDO.getPrice());
            vo.setStock(productsDO.getStock());
            vo.setSold(productsDO.getSold());
            vo.setImageUrl(productsDO.getImageUrl());
            vo.setBrand(productsDO.getBrand());
            vo.setOrigin(productsDO.getOrigin());
            return vo;
        }).toList();

        return ResultVO.success(productListVOList, page.getTotal());
    }

    @Override
    @Transactional
    public ResultVO<ProductDetailVO> findById(Long id) {
        ProductsDO productDO = this.productsMapper.selectById(id);

        if (productDO == null) {
            return ResultVO.fail("数据不存在");
        }

        //查询详情图
        List<ProductImagesDO> productImagesDOS = productImagesMapper.selectList(
                new QueryWrapper<ProductImagesDO>().eq("product_id", id)
        );


        List<ProductImageItemVO> productImageItemVOS = productImagesDOS.stream().map(detail -> {
            ProductImageItemVO productImageItemVO = new ProductImageItemVO();

            productImageItemVO.setId(detail.getId());
            productImageItemVO.setImage(detail.getImage());

            return productImageItemVO;
        }).toList();

        //查询sku
        List<SkuDO> skuDOS = skuMapper.selectList(
                new QueryWrapper<SkuDO>().eq("product_id", id)
        );


        List<SkuListVO> skuListVOList = skuDOS.stream().map(detail -> {
            SkuListVO skuListVO = new SkuListVO();

            skuListVO.setId(detail.getId());
            skuListVO.setSkuName(detail.getSkuName());
            skuListVO.setStock(detail.getStock());
            skuListVO.setSkuPrice(detail.getSkuPrice());

            return skuListVO;
        }).toList();

        List<ReviewsDO> reviewsDOList = this.reviewsMapper.selectList(
                new LambdaQueryWrapper<ReviewsDO>().eq(ReviewsDO::getProductId, id)
                        .orderByDesc(ReviewsDO::getCreatedAt)
                        .last("LIMIT 2")
        );
        List<ReviewsListVO> reviewsListVOList = reviewsDOList.stream().map(reviewsDO -> {
            ReviewsListVO reviewsListVO = new ReviewsListVO();
            reviewsListVO.setId(reviewsDO.getId());
            reviewsListVO.setRating(reviewsDO.getRating());
            reviewsListVO.setComment(reviewsDO.getComment());
            reviewsListVO.setCreatedAt(reviewsDO.getCreatedAt());

            // 查询用户昵称
            UserDO user = this.userMapper.selectById(reviewsDO.getUserId());
            if (user != null) {
                reviewsListVO.setNickname(user.getNickname());
            }

            return reviewsListVO;
        }).toList();

        ProductDetailVO detailVO = new ProductDetailVO();
        detailVO.setId(productDO.getId());
        detailVO.setUserId(productDO.getUserId());
        detailVO.setName(productDO.getName());
        detailVO.setDescription(productDO.getDescription());
        detailVO.setOriginalPrice(productDO.getOriginalPrice());
        detailVO.setPrice(productDO.getPrice());
        detailVO.setStock(productDO.getStock());
        detailVO.setUnit(productDO.getUnit());
        detailVO.setSold(productDO.getSold());
        detailVO.setImageUrl(productDO.getImageUrl());
        detailVO.setCreatedAt(productDO.getCreatedAt());
        detailVO.setBrand(productDO.getBrand());
        detailVO.setOrigin(productDO.getOrigin());
        detailVO.setImageList(productImageItemVOS);
        detailVO.setReviewList(reviewsListVOList);
        detailVO.setSkuList(skuListVOList);

        return ResultVO.success(detailVO);
    }

    @Override
    public ResultVO<Void> updateBySold(Long id, Integer quantity) {
        ProductsDO productsDO = productsMapper.selectById(id);

        if (productsDO == null) {
            ResultVO.fail("商品不存在");
        }

        assert productsDO != null;
        int currentSold = productsDO.getSold() == null ? 0 : productsDO.getSold();
        productsDO.setSold(currentSold + quantity);
        boolean result = productsMapper.updateById(productsDO) > 0;
        if (result) {
            return ResultVO.success();
        } else {
            return ResultVO.fail();
        }

    }

}




