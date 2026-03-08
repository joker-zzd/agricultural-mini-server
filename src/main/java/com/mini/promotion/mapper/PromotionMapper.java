package com.mini.promotion.mapper;

import com.mini.promotion.domain.Promotion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 19256
* @description 针对表【promotion(促销活动，形式多种多样，例如：优惠券)】的数据库操作Mapper
* @createDate 2026-02-28 15:36:51
* @Entity com.mini.promotion.domain.Promotion
*/
@Mapper
public interface PromotionMapper extends BaseMapper<Promotion> {

}




