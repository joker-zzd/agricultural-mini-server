package com.mini.categories.service;

import com.mini.categories.domain.CategoriesDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mini.resultvo.ResultVO;

import java.util.List;

/**
* @author 19256
* @description 针对表【categories(分类表)】的数据库操作Service
* @createDate 2025-04-15 21:56:13
*/
public interface CategoriesService extends IService<CategoriesDO> {

    ResultVO<List<CategoriesDO>> findByPage();

}
