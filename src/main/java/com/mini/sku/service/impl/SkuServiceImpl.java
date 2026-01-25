package com.mini.sku.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mini.resultvo.ResultVO;
import com.mini.sku.domain.SkuDO;
import com.mini.sku.service.SkuService;
import com.mini.sku.mapper.SkuMapper;
import org.springframework.stereotype.Service;

/**
 * @author 19256
 * @description 针对表【sku(商品 SKU 表)】的数据库操作Service实现
 * @createDate 2025-04-16 15:00:27
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, SkuDO>
        implements SkuService {
    private final SkuMapper skuMapper;

    SkuServiceImpl(SkuMapper skuMapper) {
        this.skuMapper = skuMapper;
    }

    @Override
    public ResultVO<Void> updateByStock(Long id, Integer stock) {
        SkuDO skuDO = this.skuMapper.selectById(id);

        if (skuDO == null) {
            return ResultVO.fail("sku为空");
        }
        Integer counter = skuDO.getStock() - stock;
        skuDO.setStock(counter);

        boolean result=this.skuMapper.updateById(skuDO)>0;
        if (result){
            return ResultVO.success();
        }else{
            return ResultVO.fail();
        }
    }
}




