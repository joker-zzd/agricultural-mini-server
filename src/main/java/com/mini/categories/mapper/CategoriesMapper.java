package com.mini.categories.mapper;

import com.mini.categories.domain.CategoriesDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 19256
* @description 针对表【categories(分类表)】的数据库操作Mapper
* @createDate 2025-04-15 21:56:13
* @Entity com.mini.categories.domain.Categories
*/
@Mapper
public interface CategoriesMapper extends BaseMapper<CategoriesDO> {

}




