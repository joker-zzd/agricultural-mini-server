package com.mini.orders.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mini.orders.constant.OrderStatusEnum;
import com.mini.orders.domain.OrdersDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mini.orders.domain.dto.CreateOrderDTO;
import com.mini.orders.domain.dto.LatestOrderDTO;
import com.mini.orders.domain.vo.OrderListVO;
import com.mini.resultvo.ResultVO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @author 19256
 * @description 针对表【orders(订单表)】的数据库操作Service
 * @createDate 2025-04-20 04:00:57
 */
public interface OrdersService extends IService<OrdersDO> {
    ResultVO<Void> createOrder(CreateOrderDTO createOrderDTO) throws JsonProcessingException;

    ResultVO<LatestOrderDTO> getLatestOrderNo();

    ResultVO<List<OrderListVO>> findByPage(Integer currenPage,Integer pageSize,String orderNo,String status);

    ResultVO<Void> updateAddress(@NotNull(message = "订单id不能为空") Long id, String address);

    ResultVO<Void> deleteAllById(@NotEmpty(message = "ids不能为空") Set<Long> ids);

    ResultVO<Void> updateStatus(@NotNull(message = "订单id不能为空") Long id, OrderStatusEnum status);

}
