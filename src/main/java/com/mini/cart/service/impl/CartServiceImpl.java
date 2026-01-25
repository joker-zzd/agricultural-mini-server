package com.mini.cart.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mini.cart.domain.CartDO;
import com.mini.cart.domain.dto.AddCartDTO;
import com.mini.cart.domain.vo.CartListVO;
import com.mini.cart.service.CartService;
import com.mini.cart.mapper.CartMapper;
import com.mini.filter.LoginUser;
import com.mini.product.domain.ProductsDO;
import com.mini.product.mapper.ProductsMapper;
import com.mini.resultvo.ResultVO;
import com.mini.sku.domain.SkuDO;
import com.mini.sku.mapper.SkuMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author 19256
 * @description 针对表【cart(购物车表)】的数据库操作Service实现
 * @createDate 2025-04-16 15:31:36
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, CartDO>
        implements CartService {

    private final CartMapper cartMapper;
    private final SkuMapper skuMapper;
    private final ProductsMapper productsMapper;

    public CartServiceImpl(CartMapper cartMapper,
                           SkuMapper skuMapper,
                           ProductsMapper productsMapper) {
        this.cartMapper = cartMapper;
        this.skuMapper = skuMapper;
        this.productsMapper = productsMapper;
    }


    @Override
    @Transactional
    public ResultVO<List<CartListVO>> findByPage(Integer currentPage, Integer pageSize) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUserId();

        // 分页查询购物车数据
        Page<CartDO> page = new Page<>(currentPage, pageSize);
        Page<CartDO> cartPage = this.lambdaQuery()
                .orderByDesc(CartDO::getCreatedAt)
                .eq(CartDO::getUserId, userId)
                .page(page);

        List<CartListVO> voList = cartPage.getRecords().stream().map(cartDO -> {
            CartListVO vo = new CartListVO();
            vo.setId(cartDO.getId());
            vo.setQuantity(cartDO.getQuantity());
            vo.setChecked(cartDO.getChecked());
            vo.setProductId(cartDO.getProductId());

            // 查询商品信息
            ProductsDO product = productsMapper.selectById(cartDO.getProductId());
            if (product != null) {
                vo.setName(product.getName());
                vo.setDescription(product.getDescription());
                vo.setImageUrl(product.getImageUrl());
            }

            // 查询 SKU 信息
            SkuDO sku = skuMapper.selectById(cartDO.getSkuId());
            if (sku != null) {
                vo.setSkuName(sku.getSkuName());
                vo.setPrice(sku.getSkuPrice());
            }

            return vo;
        }).toList();

        return ResultVO.success(voList, cartPage.getTotal());
    }


    @Override
    public ResultVO<Void> add(AddCartDTO addCartDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUserId();

        CartDO cartDO = new CartDO();
        cartDO.setUserId(userId);
        cartDO.setProductId(addCartDTO.getProductId());
        cartDO.setSkuId(addCartDTO.getSkuId());
        cartDO.setQuantity(addCartDTO.getQuantity());
        int i = this.cartMapper.insert(cartDO);
        if (i > 0) {
            return ResultVO.success();
        } else {
            return ResultVO.fail();
        }
    }

    @Override
    public ResultVO<Void> deleteAllById(Set<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return ResultVO.fail("ID 不能为空");
        }
        int deleteBatchIds = cartMapper.deleteBatchIds(ids);
        if (deleteBatchIds != 0) {
            return ResultVO.success("操作成功");
        } else {
            return ResultVO.fail();
        }
    }
}




