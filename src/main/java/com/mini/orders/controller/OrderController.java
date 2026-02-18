package com.mini.orders.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mini.orders.constant.OrderStatusEnum;
import com.mini.orders.domain.dto.CreateOrderDTO;
import com.mini.orders.domain.dto.LatestOrderDTO;
import com.mini.orders.domain.vo.OrderListVO;
import com.mini.orders.service.OrdersService;
import com.mini.resultvo.ResultVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/api/order")

public class OrderController {
    private final OrdersService ordersService;

    OrderController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping("/add")
    public ResultVO<Void> createOrder(@RequestBody @Valid CreateOrderDTO createOrderDTO) throws JsonProcessingException {
        return this.ordersService.createOrder(createOrderDTO);
    }

    @GetMapping("/latestOrderNo")
    public ResultVO<LatestOrderDTO> getLatestOrderNo() {
        return ordersService.getLatestOrderNo();
    }

    @GetMapping("/findByPage")
    public ResultVO<List<OrderListVO>> findByPage(@RequestParam(name = "currenPage", defaultValue = "1", required = false) Integer currenPage,
                                                  @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                                  @RequestParam(name = "orderNo", defaultValue = "", required = false) String orderNo,
                                                  @RequestParam(name = "status", defaultValue = "", required = false) String status) {
        return this.ordersService.findByPage(currenPage, pageSize, orderNo, status);
    }

    @PutMapping("/updateAddress")
    public ResultVO<Void> updateAddress(Long id, String address) {
        return this.ordersService.updateAddress(id, address);
    }

    @PutMapping("/updateStatus")
    public ResultVO<Void> updateStatus(Long id, OrderStatusEnum status) {
        return this.ordersService.updateStatus(id, status);
    }

    @DeleteMapping("/delete")
    public ResultVO<Void> deleteAllById(@NotEmpty(message = "ids不能为空") @RequestBody Set<Long> ids) {
        return ordersService.deleteAllById(ids);
    }
}
