package com.mini.categories.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mini.categories.domain.CategoriesDO;
import com.mini.categories.service.CategoriesService;
import com.mini.categories.mapper.CategoriesMapper;
import com.mini.resultvo.ResultVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 19256
 * @description 针对表【categories(分类表)】的数据库操作Service实现
 * @createDate 2025-04-15 21:56:13
 */
@Service
public class CategoriesServiceImpl extends ServiceImpl<CategoriesMapper, CategoriesDO>
        implements CategoriesService {
    private final CategoriesMapper categoriesMapper;

    public CategoriesServiceImpl(CategoriesMapper categoriesMapper) {
        this.categoriesMapper = categoriesMapper;
    }

    @Override
    public ResultVO<List<CategoriesDO>> findByPage() {
        List<CategoriesDO> categoriesDOList = this.categoriesMapper.selectList(
                new LambdaQueryWrapper<>()
        );
        if (!categoriesDOList.isEmpty()){
            return  ResultVO.success(categoriesDOList);
        }else{
            return ResultVO.fail("无分类数据");
        }
    }
}




