package com.mini.configuration.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.OrderItem;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.configuration.AlipayConfiguration;
import com.mini.configuration.service.PayService;
import com.mini.orderdetail.domain.OrderDetailDO;
import com.mini.orderdetail.domain.dto.OrderItemDTO;
import com.mini.orderdetail.mapper.OrderDetailMapper;
import com.mini.orders.constant.OrderStatusEnum;
import com.mini.orders.domain.OrdersDO;
import com.mini.orders.mapper.OrdersMapper;
import com.mini.product.service.ProductsService;
import com.mini.product.service.impl.ProductsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PayServiceImpl implements PayService {
    private final AlipayConfiguration alipayConfiguration;
    private AlipayClient alipayClient;
    private final OrdersMapper ordersMapper;
    private final OrderDetailMapper orderDetailMapper;
    private final ProductsService productsService;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final Logger log = LoggerFactory.getLogger(PayServiceImpl.class);


    public PayServiceImpl(AlipayConfiguration alipayConfiguration,
                          OrdersMapper ordersMapper,
                          OrderDetailMapper orderDetailMapper,
                          ProductsService productsService,
                          KafkaTemplate<String, Object> kafkaTemplate) {
        this.alipayConfiguration = alipayConfiguration;
        this.ordersMapper = ordersMapper;
        this.orderDetailMapper = orderDetailMapper;
        this.productsService = productsService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostConstruct
    public void init() {
        alipayClient = new DefaultAlipayClient(
                alipayConfiguration.getGatewayUrl(),
                alipayConfiguration.getAppId(),
                alipayConfiguration.getPrivateKey(),
                alipayConfiguration.getFormat(),
                alipayConfiguration.getCharset(),
                alipayConfiguration.getPublicKey(),
                alipayConfiguration.getSignType()
        );
    }

    @Override
    public String payment(String orderNo, BigDecimal totalAmount, String subject) throws AlipayApiException {
        AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();

        alipayTradePagePayRequest.setNotifyUrl(alipayConfiguration.getNotifyUrl());
        alipayTradePagePayRequest.setReturnUrl(alipayConfiguration.getReturnUrl());

        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", orderNo);
        bizContent.put("total_amount", totalAmount);
        bizContent.put("subject", subject);
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");

        alipayTradePagePayRequest.setBizContent(bizContent.toJSONString());

        return alipayClient.pageExecute(alipayTradePagePayRequest).getBody();
    }

    @Override
    public String handleNotify(HttpServletRequest request) throws Exception {
        // 1. 将支付宝POST过来的参数转换为Map<String, String>
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();

        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = String.join(",", values);
            params.put(name, valueStr);
        }

        log.info("收到支付宝异步通知参数: {}", params);

        // 2. 验证签名，防止伪造请求
        boolean signVerified = AlipaySignature.rsaCheckV1(
                params,
                alipayConfiguration.getPublicKey(),
                alipayConfiguration.getCharset(),
                alipayConfiguration.getSignType()
        );


        if (!signVerified) {
            log.error("支付宝异步通知验签失败！");
            return "fail";
        }

        // 3. 验签成功后，处理业务逻辑
        String outTradeNo = params.get("out_trade_no"); // 商户订单号
        String tradeStatus = params.get("trade_status"); // 交易状态

        log.info("订单号: {}, 交易状态: {}", outTradeNo, tradeStatus);

        if ("TRADE_SUCCESS".equals(tradeStatus) || "TRADE_FINISHED".equals(tradeStatus)) {
            OrdersDO order = ordersMapper.selectByOrderNo(outTradeNo);
            if (order != null) {
                if (OrderStatusEnum.WAIT_PAYMENT.equals(order.getStatus())) {
                    order.setStatus(OrderStatusEnum.WAIT_DELIVER); // 修改订单状态为待发货
                    order.setPaymentMethod("ALIPAY"); // 支付方式为支付宝
                    ordersMapper.updateById(order);
                    log.info("订单 {} 支付成功，状态更新为待发货", outTradeNo);

                    //查询订单详情
                    List<OrderDetailDO> orderDetailDOList = this.orderDetailMapper.selectList(
                            new QueryWrapper<OrderDetailDO>().eq("order_id", order.getId())
                    );
                    for (OrderDetailDO orderDetailDO : orderDetailDOList) {
                        Long productId = orderDetailDO.getProductId();
                        Integer quantity = orderDetailDO.getQuantity();
                        this.productsService.updateBySold(productId, quantity);
                    }

                    List<OrderItemDTO> orderItemDTOS = orderDetailDOList.stream().map(detail -> {
                        OrderItemDTO dto = new OrderItemDTO();
                        dto.setProductId(detail.getProductId());
                        dto.setQuantity(detail.getQuantity());
                        dto.setPrice(detail.getPrice());
                        dto.setName(detail.getName());
                        dto.setImage(detail.getImage());
                        dto.setDescription(detail.getDescription()); // 如果有
                        return dto;
                    }).toList();
                    Map<String, Object> message = new HashMap<>();

                    message.put("type", "NEW_ORDER");
                    message.put("orderId", order.getId());
                    message.put("orderNo", order.getOrderNo());
                    message.put("orderItems", orderItemDTOS);

                    String messageJson = new ObjectMapper().writeValueAsString(message);
                    kafkaTemplate.send("merchant-notify-topic", messageJson);
                } else {
                    log.warn("订单 {} 当前状态为 {}，无需重复更新", outTradeNo, order.getStatus());
                }
            } else {
                log.error("订单号 {} 不存在", outTradeNo);
                return "fail";
            }
        } else {
            log.warn("交易状态 {} 不处理", tradeStatus);
            return "fail";
        }

        // 4. 返回success告诉支付宝服务器回调成功，避免重复回调
        return "success";
    }

}
