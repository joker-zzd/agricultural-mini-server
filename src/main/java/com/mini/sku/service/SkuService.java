package com.mini.sku.service;

import com.mini.resultvo.ResultVO;
import com.mini.sku.domain.SkuDO;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.validation.constraints.NotNull;

/**
* @author 19256
* @description 针对表【sku(商品 SKU 表)】的数据库操作Service
* @createDate 2025-04-16 15:00:27
*/
public interface SkuService extends IService<SkuDO> {
    ResultVO<Void> updateByStock(@NotNull(message = "id不能为空") Long id ,@NotNull(message = "数量不能为空") Integer stock);

}
