package com.mini.productimage.mapper;

import com.mini.productimage.domain.ProductImagesDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 19256
* @description 针对表【product_images(商品图片表)】的数据库操作Mapper
* @createDate 2025-04-16 10:29:08
* @Entity com.mini.productimage.domain.ProductImages
*/
@Mapper
public interface ProductImagesMapper extends BaseMapper<ProductImagesDO> {

}




