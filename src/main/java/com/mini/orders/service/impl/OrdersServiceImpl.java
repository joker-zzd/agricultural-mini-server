package com.mini.orders.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.filter.LoginUser;
import com.mini.orderdetail.domain.OrderDetailDO;
import com.mini.orderdetail.domain.dto.OrderItemDTO;
import com.mini.orderdetail.domain.vo.OrderItemVO;
import com.mini.orderdetail.mapper.OrderDetailMapper;
import com.mini.orders.constant.OrderStatusEnum;
import com.mini.orders.domain.OrdersDO;
import com.mini.orders.domain.dto.CreateOrderDTO;
import com.mini.orders.domain.dto.LatestOrderDTO;
import com.mini.orders.domain.vo.OrderListVO;
import com.mini.orders.service.OrdersService;
import com.mini.orders.mapper.OrdersMapper;
import com.mini.resultvo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 19256
 * @description 针对表【orders(订单表)】的数据库操作Service实现
 * @createDate 2025-04-20 04:00:57
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, OrdersDO>
        implements OrdersService {
    private final OrdersMapper ordersMapper;
    private final OrderDetailMapper orderDetailMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrdersServiceImpl(OrdersMapper ordersMapper,
                             OrderDetailMapper orderDetailMapper,
                             KafkaTemplate<String, Object> kafkaTemplate) {
        this.ordersMapper = ordersMapper;
        this.orderDetailMapper = orderDetailMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional
    public ResultVO<Void> createOrder(CreateOrderDTO createOrderDTO) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUserId();

        OrdersDO ordersDO = new OrdersDO();
        ordersDO.setTotalAmount(createOrderDTO.getTotalAmount());

        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        String orderNo = snowflake.nextIdStr();
        ordersDO.setOrderNo(orderNo);
        ordersDO.setUserId(userId);
        int rows = ordersMapper.insert(ordersDO);

        if (rows <= 0) {
            return ResultVO.fail("订单创建失败");
        }

        Long orderId = ordersDO.getId();

        List<OrderItemDTO> orderItemDTOS = createOrderDTO.getOrderItems();
        if (orderItemDTOS == null || orderItemDTOS.isEmpty()) {
            return ResultVO.fail("订单详情不能为空");
        }

        List<OrderDetailDO> orderDetailDOList = new ArrayList<>();
        for (OrderItemDTO item : orderItemDTOS) {
            OrderDetailDO orderDetailDO = new OrderDetailDO();

            orderDetailDO.setOrderId(orderId);
            orderDetailDO.setProductId(item.getProductId());
            orderDetailDO.setQuantity(item.getQuantity());
            orderDetailDO.setPrice(item.getPrice());
            orderDetailDO.setImage(item.getImage());
            orderDetailDO.setName(item.getName());
            orderDetailDO.setDescription(item.getDescription());
            orderDetailDOList.add(orderDetailDO);
        }

        for (OrderDetailDO item : orderDetailDOList) {
            this.orderDetailMapper.insert(item);
        }
//
//        Map<String, Object> message = new HashMap<>();
//        message.put("type", "NEW_ORDER");
//        message.put("orderId", orderId);
//        message.put("orderNo", orderNo);
//        message.put("orderItems", orderItemDTOS);//商品信息
//        message.put("timestamp", System.currentTimeMillis());
//
//        //转成json发送kafka消息
//        String messageJson = new ObjectMapper().writeValueAsString(message);
//        kafkaTemplate.send("merchant-notify-topic", messageJson);

        return ResultVO.success();

    }

    @Override
    public ResultVO<LatestOrderDTO> getLatestOrderNo() {
        OrdersDO latestOrder = lambdaQuery()
                .orderByDesc(OrdersDO::getCreatedAt)
                .last("limit 1")
                .one();

        if (latestOrder == null) {
            return ResultVO.fail("暂无订单数据");
        }

        LatestOrderDTO dto = new LatestOrderDTO(latestOrder.getId(), latestOrder.getOrderNo());
        return ResultVO.ok(dto);
    }

    @Override
    @Transactional
    public ResultVO<List<OrderListVO>> findByPage(Integer currenPage, Integer pageSize, String orderNo, String status) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUserId();

        Page<OrdersDO> page = new Page<>(currenPage, pageSize);
        LambdaQueryWrapper<OrdersDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(OrdersDO::getCreatedAt)
                .eq(userId != null, OrdersDO::getUserId, userId)
                .eq(StringUtils.isNotBlank(orderNo), OrdersDO::getOrderNo, orderNo)
                .eq(StringUtils.isNotBlank(status), OrdersDO::getStatus, status);

        this.ordersMapper.selectPage(page, wrapper);
        List<OrdersDO> ordersDOS = page.getRecords();

        List<OrderListVO> orderListVOList = ordersDOS.stream().map(order -> {
            OrderListVO vo = new OrderListVO();
            BeanUtils.copyProperties(order, vo);

            // 查询该订单的订单项
            List<OrderItemVO> items = orderDetailMapper.selectList(
                    new LambdaQueryWrapper<OrderDetailDO>().eq(OrderDetailDO::getOrderId, order.getId())
            ).stream().map(item -> {
                OrderItemVO itemVO = new OrderItemVO();
                itemVO.setId(item.getId());
                itemVO.setProductId(item.getProductId());
                itemVO.setProductId(item.getProductId());
                itemVO.setImage(item.getImage());
                itemVO.setQuantity(item.getQuantity());
                itemVO.setPrice(item.getPrice());
                itemVO.setName(item.getName());
                itemVO.setDescription(item.getDescription());
                itemVO.setCommented(item.getCommented());
                return itemVO;
            }).collect(Collectors.toList());

            vo.setOrderItem(items);
            return vo;
        }).collect(Collectors.toList());

        return ResultVO.success(orderListVOList, page.getTotal());
    }

    @Override
    public ResultVO<Void> updateAddress(Long id, String address) {
        OrdersDO ordersDO = new OrdersDO();

        ordersDO.setId(id);
        ordersDO.setShippingAddress(address);

        boolean result = this.ordersMapper.updateById(ordersDO) > 0;
        if (result) {
            return ResultVO.success();
        } else {
            return ResultVO.fail();
        }
    }

    @Override
    @Transactional
    public ResultVO<Void> deleteAllById(Set<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return ResultVO.fail("ID 不能为空");
        }

        this.orderDetailMapper.delete(
                new QueryWrapper<OrderDetailDO>().in("order_id", ids)
        );
        boolean result = ordersMapper.deleteBatchIds(ids) > 0;
        if (result) {
            return ResultVO.success();
        } else {
            return ResultVO.fail();
        }
    }

    @Override
    public ResultVO<Void> updateStatus(Long id, OrderStatusEnum status) {
        OrdersDO ordersDO = new OrdersDO();

        ordersDO.setId(id);
        ordersDO.setStatus(status);

        boolean result = this.ordersMapper.updateById(ordersDO) > 0;
        if (result) {
            return ResultVO.success();
        } else {
            return ResultVO.fail();
        }
    }

    @Scheduled(cron = "0 */2 * * * ?") // 定时任务 每2分钟执行一次
    @Transactional
    public void updateOrderStatus() {
        // 1. 查询所有订单（你可以加时间或其他条件以优化）
        QueryWrapper<OrdersDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("status", OrderStatusEnum.WAIT_DELIVER, OrderStatusEnum.WAIT_RECEIVE);

        List<OrdersDO> orders = ordersMapper.selectList(queryWrapper);

        if (orders.isEmpty()) {
            return;
        }

        // 2. 遍历并按条件修改状态
        for (OrdersDO order : orders) {
            if (OrderStatusEnum.WAIT_DELIVER.equals(order.getStatus())) {
                order.setStatus(OrderStatusEnum.WAIT_RECEIVE);
            } else if (OrderStatusEnum.WAIT_RECEIVE.equals(order.getStatus())) {
                order.setStatus(OrderStatusEnum.FINISHED);
            }

            ordersMapper.updateById(order); // 逐条更新（也可以优化为批量更新）
        }

        System.out.println("定时任务：已更新订单状态，共处理订单数：" + orders.size());
    }


}




