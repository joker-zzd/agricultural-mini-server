package com.mini.sku.mapper;

import com.mini.sku.domain.SkuDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 19256
* @description 针对表【sku(商品 SKU 表)】的数据库操作Mapper
* @createDate 2025-04-16 15:00:27
* @Entity com.mini.sku.domain.Sku
*/
@Mapper
public interface SkuMapper extends BaseMapper<SkuDO> {

}




