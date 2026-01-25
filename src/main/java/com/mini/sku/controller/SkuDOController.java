package com.mini.sku.controller;

import com.mini.resultvo.ResultVO;
import com.mini.sku.service.SkuService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/sku")
public class SkuDOController {
    private final SkuService skuService;

    public SkuDOController(SkuService skuService) {
        this.skuService = skuService;
    }
    @PutMapping("/updateByStock")
    public ResultVO<Void> updateByStock(Long id, Integer stock) {
        return this.skuService.updateByStock(id, stock);
    }
}
