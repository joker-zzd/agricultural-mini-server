package com.mini.orders.domain.dto;

import lombok.Data;

@Data
public class LatestOrderDTO {
    private Long id;
    private String orderNo;

    public LatestOrderDTO(Long id, String orderNo){
        this.id=id;
        this.orderNo=orderNo;
    }
}

