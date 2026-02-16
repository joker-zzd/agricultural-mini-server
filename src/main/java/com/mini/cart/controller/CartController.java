package com.mini.cart.controller;

import com.mini.cart.domain.dto.AddCartDTO;
import com.mini.cart.domain.vo.CartListVO;
import com.mini.cart.service.CartService;
import com.mini.resultvo.ResultVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/findByPage")
    public ResultVO<List<CartListVO>> findByPage(
            @RequestParam(name = "currentPage", defaultValue = "1", required = false) Integer currentPage,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize
    ) {
        return this.cartService.findByPage(currentPage, pageSize);
    }

    @PostMapping("/add")
    public ResultVO<Void> add(@RequestBody @Valid AddCartDTO addCartDTO) {
        return this.cartService.add(addCartDTO);
    }

    @DeleteMapping("/delete")
    public ResultVO<Void> deleteAllById(@NotEmpty(message = "ids不能为空") @RequestBody Set<Long> ids) {
        return cartService.deleteAllById(ids);
    }
}
