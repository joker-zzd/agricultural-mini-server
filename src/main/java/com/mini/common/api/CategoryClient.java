package com.mini.common.api;

import com.mini.common.api.dot.CategoryBasicDTO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface CategoryClient {

    /**
     * 获取所有课程及课程分类
     * @return  所有课程及课程分类
     */
    @GetMapping("getAllOfOneLevel")
    List<CategoryBasicDTO> getAllOfOneLevel();
}
