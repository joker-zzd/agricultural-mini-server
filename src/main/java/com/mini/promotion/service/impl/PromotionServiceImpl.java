package com.mini.promotion.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mini.promotion.domain.Promotion;
import com.mini.promotion.service.PromotionService;
import com.mini.promotion.mapper.PromotionMapper;
import org.springframework.stereotype.Service;

/**
* @author 19256
* @description 针对表【promotion(促销活动，形式多种多样，例如：优惠券)】的数据库操作Service实现
* @createDate 2026-02-28 15:36:51
*/
@Service
public class PromotionServiceImpl extends ServiceImpl<PromotionMapper, Promotion>
    implements PromotionService{

}




