package com.mini.product.mapper;

import com.mini.product.domain.ProductsDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 19256
* @description 针对表【products(商品表)】的数据库操作Mapper
* @createDate 2025-04-15 22:34:47
* @Entity com.mini.product.domain.Products
*/
@Mapper
public interface ProductsMapper extends BaseMapper<ProductsDO> {

}




