package com.mini.configuration.service;

import com.alipay.api.AlipayApiException;
import com.mini.orders.domain.OrdersDO;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public interface PayService {
    String payment(String orderNo, BigDecimal totalAmount, String subject) throws AlipayApiException;

    String handleNotify(HttpServletRequest httpServletRequest) throws Exception;
}
