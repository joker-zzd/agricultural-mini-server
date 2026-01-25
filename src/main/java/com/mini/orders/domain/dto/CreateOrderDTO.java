package com.mini.orders.domain.dto;

import com.mini.orderdetail.domain.dto.OrderItemDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateOrderDTO {
    /**
     * 总金额不能为空
     */
    private BigDecimal totalAmount;

    private List<OrderItemDTO> orderItems;
}
