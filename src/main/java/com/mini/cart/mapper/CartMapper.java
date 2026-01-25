package com.mini.cart.mapper;

import com.mini.cart.domain.CartDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 19256
* @description 针对表【cart(购物车表)】的数据库操作Mapper
* @createDate 2025-04-16 15:31:36
* @Entity com.mini.cart.domain.Cart
*/
@Mapper
public interface CartMapper extends BaseMapper<CartDO> {

}




