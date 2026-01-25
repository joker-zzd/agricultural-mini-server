package com.mini.cart.service;

import com.mini.cart.domain.CartDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mini.cart.domain.dto.AddCartDTO;
import com.mini.cart.domain.vo.CartListVO;
import com.mini.resultvo.ResultVO;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

/**
* @author 19256
* @description 针对表【cart(购物车表)】的数据库操作Service
* @createDate 2025-04-16 15:31:36
*/
public interface CartService extends IService<CartDO> {

    ResultVO<List<CartListVO>> findByPage(Integer currentPage, Integer pageSize);

    ResultVO<Void> add(AddCartDTO addCartDTO);

    ResultVO<Void> deleteAllById(@NotEmpty(message = "ids不能为空") Set<Long> ids);
}
