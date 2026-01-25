package com.mini.productimage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mini.productimage.domain.ProductImagesDO;
import com.mini.productimage.service.ProductImagesService;
import com.mini.productimage.mapper.ProductImagesMapper;
import org.springframework.stereotype.Service;

/**
* @author 19256
* @description 针对表【product_images(商品图片表)】的数据库操作Service实现
* @createDate 2025-04-16 10:29:08
*/
@Service
public class ProductImagesServiceImpl extends ServiceImpl<ProductImagesMapper, ProductImagesDO>
    implements ProductImagesService{

}




