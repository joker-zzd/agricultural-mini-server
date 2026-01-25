package com.mini.categories.controller;

import com.mini.categories.domain.CategoriesDO;
import com.mini.categories.service.CategoriesService;
import com.mini.resultvo.ResultVO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/categories")
public class CategoriesController {

    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping("list")
    public ResultVO<List<CategoriesDO>> findByPage() {
        return this.categoriesService.findByPage();
    }
}
