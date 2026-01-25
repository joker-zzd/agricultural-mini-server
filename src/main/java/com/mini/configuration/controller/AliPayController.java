package com.mini.configuration.controller;

import com.mini.configuration.service.PayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/alipay")
public class AliPayController {

    private final PayService payService;

    public AliPayController(PayService payService) {
        this.payService = payService;
    }

    @PostMapping(value = "/pay")
    public ResponseEntity<String> payment(@RequestBody Map<String, Object> params) {
        try{
            String orderNo = (String) params.get("orderNo");
            BigDecimal totalAmount = new BigDecimal(params.get("totalAmount").toString());
            String subject = (String) params.get("subject");

            String form = payService.payment(orderNo, totalAmount, subject);
            return ResponseEntity.ok(form);
        }catch(Exception e){
            return ResponseEntity.status(500).body("支付创建失败: " + e.getMessage());
        }
    }

    @PostMapping("/notify")
    public String handleNotify(HttpServletRequest request) throws Exception {
        return payService.handleNotify(request);
    }
}
