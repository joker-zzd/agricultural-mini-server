package com.mini.reviews.mapper;

import com.mini.reviews.domain.ReviewsDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 19256
* @description 针对表【reviews(评价表)】的数据库操作Mapper
* @createDate 2025-04-16 11:01:08
* @Entity com.mini.reviews.domain.Reviews
*/
@Mapper
public interface ReviewsMapper extends BaseMapper<ReviewsDO> {

}




