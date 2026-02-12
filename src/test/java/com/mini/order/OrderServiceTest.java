//package com.mini.order;
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.mini.filter.LoginUser;
//import com.mini.orderdetail.domain.OrderDetailDO;
//import com.mini.orderdetail.domain.dto.OrderItemDTO;
//import com.mini.orderdetail.mapper.OrderDetailMapper;
//import com.mini.orders.constant.OrderStatusEnum;
//import com.mini.orders.domain.OrdersDO;
//import com.mini.orders.domain.dto.CreateOrderDTO;
//import com.mini.orders.domain.dto.LatestOrderDTO;
//import com.mini.orders.domain.vo.OrderListVO;
//import com.mini.orders.mapper.OrdersMapper;
//import com.mini.orders.service.impl.OrdersServiceImpl;
//import com.mini.resultvo.ResultVO;
//import com.mini.resultvo.StatusCode;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.math.BigDecimal;
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
///**
// * OrdersServiceImpl 单元测试类
// * 覆盖所有主要业务方法的测试场景
// */
//@ExtendWith(MockitoExtension.class)
//class OrderServiceTest {
//
//    @Mock
//    private OrdersMapper ordersMapper;
//
//    @Mock
//    private OrderDetailMapper orderDetailMapper;
//
//    @Mock
//    private KafkaTemplate<String, Object> kafkaTemplate;
//
//    @InjectMocks
//    private OrdersServiceImpl ordersService;
//
//    // 测试数据实例变量
//    private CreateOrderDTO validCreateOrderDTO;
//    private CreateOrderDTO emptyOrderItemsDTO;
//    private List<OrderItemDTO> orderItems;
//    private OrderItemDTO orderItem1;
//    private OrderItemDTO orderItem2;
//    private OrdersDO sampleOrdersDO;
//    private OrderDetailDO sampleOrderDetail1;
//    private OrderDetailDO sampleOrderDetail2;
//
//    /**
//     * 测试前初始化基础数据
//     */
//    @BeforeEach
//    void setUp() {
//        // 初始化订单项
//        orderItem1 = new OrderItemDTO();
//        orderItem1.setProductId(1L);
//        orderItem1.setQuantity(2);
//        orderItem1.setPrice(new BigDecimal("10.00"));
//        orderItem1.setImage("product1.jpg");
//        orderItem1.setName("商品1");
//        orderItem1.setDescription("商品1描述");
//
//        orderItem2 = new OrderItemDTO();
//        orderItem2.setProductId(2L);
//        orderItem2.setQuantity(1);
//        orderItem2.setPrice(new BigDecimal("20.00"));
//        orderItem2.setImage("product2.jpg");
//        orderItem2.setName("商品2");
//        orderItem2.setDescription("商品2描述");
//
//        orderItems = Arrays.asList(orderItem1, orderItem2);
//
//        // 初始化创建订单DTO
//        validCreateOrderDTO = new CreateOrderDTO();
//        validCreateOrderDTO.setTotalAmount(new BigDecimal("30.00"));
//        validCreateOrderDTO.setOrderItems(orderItems);
//
//        emptyOrderItemsDTO = new CreateOrderDTO();
//        emptyOrderItemsDTO.setTotalAmount(new BigDecimal("110.00"));
//        emptyOrderItemsDTO.setOrderItems(null);
//
//        // 初始化订单实体
//        sampleOrdersDO = new OrdersDO();
//        sampleOrdersDO.setId(1L);
//        sampleOrdersDO.setOrderNo("ORDER_123456789");
//        sampleOrdersDO.setUserId(100L);
//        sampleOrdersDO.setTotalAmount(new BigDecimal("30.00"));
//        sampleOrdersDO.setStatus(OrderStatusEnum.WAIT_PAYMENT);
//        sampleOrdersDO.setCreatedAt(new Date(System.currentTimeMillis()));
//
//        // 初始化订单详情实体
//        sampleOrderDetail1 = new OrderDetailDO();
//        sampleOrderDetail1.setId(1L);
//        sampleOrderDetail1.setOrderId(1L);
//        sampleOrderDetail1.setProductId(1L);
//        sampleOrderDetail1.setQuantity(2);
//        sampleOrderDetail1.setPrice(new BigDecimal("10.00"));
//
//        sampleOrderDetail2 = new OrderDetailDO();
//        sampleOrderDetail2.setId(2L);
//        sampleOrderDetail2.setOrderId(1L);
//        sampleOrderDetail2.setProductId(2L);
//        sampleOrderDetail2.setQuantity(1);
//        sampleOrderDetail2.setPrice(new BigDecimal("20.00"));
//    }
//
//    /**
//     * 测试创建订单 - 正常流程
//     */
//    @Test
//    void testCreateOrder_Success() throws JsonProcessingException {
//        // 设置安全上下文
//        LoginUser loginUser = new LoginUser();
//        loginUser.setUserId(1L);
//        Authentication auth = new UsernamePasswordAuthenticationToken(loginUser, null);
//        SecurityContextHolder.getContext().setAuthentication(auth);
//
//        // 模拟订单插入成功
//        when(ordersMapper.insert(any(OrdersDO.class))).thenAnswer(invocation -> {
//            OrdersDO order = invocation.getArgument(0);
//            order.setId(1L);
//            order.setOrderNo("ORDER_123456789");
//            return 1;
//        });
//
//        // 执行测试
//        ResultVO<Void> result = ordersService.createOrder(validCreateOrderDTO);
//
//        // 验证结果
//        assertEquals(StatusCode.SUCCESS, result.getCode());
//        assertEquals("成功", result.getMessage());
//
//        // 验证数据库操作
//        verify(ordersMapper, times(1)).insert(any(OrdersDO.class));
//        verify(orderDetailMapper, times(2)).insert(any(OrderDetailDO.class));
//    }
//
//    /**
//     * 测试创建订单 - 订单详情为空
//     */
//    @Test
//    void testCreateOrder_EmptyOrderItems() throws JsonProcessingException {
//        // 设置安全上下文
//        LoginUser loginUser = new LoginUser();
//        loginUser.setUserId(100L);
//        Authentication auth = new UsernamePasswordAuthenticationToken(loginUser, null);
//        SecurityContextHolder.getContext().setAuthentication(auth);
//
//        // 执行测试
//        ResultVO<Void> result = ordersService.createOrder(emptyOrderItemsDTO);
//
//        // 验证结果
//        assertEquals(StatusCode.FAIL, result.getCode());
//        assertEquals("订单详情不能为空", result.getMessage());
//
//        // 验证订单主表未被插入
//        verify(ordersMapper, never()).insert(any(OrdersDO.class));
//    }
//
//    /**
//     * 测试获取最新订单号 - 存在订单数据
//     */
//    @Test
//    void testGetLatestOrderNo_HasData() {
//        // 模拟数据库返回最新订单
//        when(ordersMapper.selectOne(any(LambdaQueryWrapper.class))).thenReturn(sampleOrdersDO);
//
//        // 执行测试
//        ResultVO<LatestOrderDTO> result = ordersService.getLatestOrderNo();
//
//        // 验证结果
//        assertEquals(StatusCode.SUCCESS, result.getCode());
//        assertNotNull(result.getData());
//        assertEquals(sampleOrdersDO.getId(), result.getData().getId());
//        assertEquals(sampleOrdersDO.getOrderNo(), result.getData().getOrderNo());
//    }
//
//    /**
//     * 测试获取最新订单号  -  不存在订单数据
//     */
//    @Test
//    void testGetLatestOrderNo_NoData() {
//        // 模拟数据库返回空
//        when(ordersMapper.selectOne(any(LambdaQueryWrapper.class))).thenReturn(null);
//
//        // 执行测试
//        ResultVO<LatestOrderDTO> result = ordersService.getLatestOrderNo();
//
//        // 验证结果
//        assertEquals(StatusCode.FAIL, result.getCode());
//        assertEquals("暂无订单数据", result.getMessage());
//    }
//
//    /**
//     * 测试分页查询 - 正常查询流程
//     */
//    @Test
//    void testFindByPage_Success() {
//        // 设置安全上下文
//        LoginUser loginUser = new LoginUser();
//        loginUser.setUserId(1L);
//        Authentication auth = new UsernamePasswordAuthenticationToken(loginUser, null);
//        SecurityContextHolder.getContext().setAuthentication(auth);
//
//        // 模拟分页查询结果
//        Page<OrdersDO> page = new Page<>();
//        page.setRecords(Arrays.asList(sampleOrdersDO));
//        page.setTotal(1);
//        when(ordersMapper.selectPage(any(Page.class), any(LambdaQueryWrapper.class))).thenReturn(page);
//
//        // 执行测试
//        ResultVO<List<OrderListVO>> result = ordersService.findByPage(1, 10, null, null);
//
//        // 验证结果
//        assertEquals(StatusCode.SUCCESS, result.getCode());
//        assertNotNull(result.getData());
//        assertEquals(0L, result.getData().size());
//        assertEquals(0L, result.getTotal());
//    }
//
//    /**
//     * 测试更新地址 - 更新成功
//     */
//    @Test
//    void testUpdateAddress_Success() {
//        // 模拟更新成功
//        when(ordersMapper.updateById(any(OrdersDO.class))).thenReturn(1);
//
//        // 执行测试
//        ResultVO<Void> result = ordersService.updateAddress(1L, "新地址");
//
//        // 验证结果
//        assertEquals(StatusCode.SUCCESS, result.getCode());
//
//        // 验证更新方法被调用
//        ArgumentCaptor<OrdersDO> captor = ArgumentCaptor.forClass(OrdersDO.class);
//        verify(ordersMapper).updateById(captor.capture());
//
//        OrdersDO capturedOrder = captor.getValue();
//        assertEquals(1L, capturedOrder.getId());
//        assertEquals("新地址", capturedOrder.getShippingAddress());
//    }
//
//    /**
//     * 测试更新地址 - 更新失败
//     */
//    @Test
//    void testUpdateAddress_Failure() {
//        // 模拟更新失败
//        when(ordersMapper.updateById(any(OrdersDO.class))).thenReturn(0);
//
//        // 执行测试
//        ResultVO<Void> result = ordersService.updateAddress(1L, "新地址");
//
//        // 验证结果
//        assertEquals(StatusCode.FAIL, result.getCode());
//    }
//
//    /**
//     * 测试批量删除 - 正常删除流程
//     */
//    @Test
//    void testDeleteAllById_Success() {
//        Set<Long> ids = new HashSet<>(Arrays.asList(1L, 2L));
//
//        // 模拟删除操作成功
//        when(orderDetailMapper.delete(any(QueryWrapper.class))).thenReturn(2);
//        when(ordersMapper.deleteBatchIds(ids)).thenReturn(2);
//
//        // 执行测试
//        ResultVO<Void> result = ordersService.deleteAllById(ids);
//
//        // 验证结果
//        assertEquals(StatusCode.SUCCESS, result.getCode());
//
//        // 验证先删除订单详情，再删除订单
//        verify(orderDetailMapper, times(1)).delete(any(QueryWrapper.class));
//        verify(ordersMapper, times(1)).deleteBatchIds(ids);
//    }
//
//    /**
//     * 测试批量删除 -  ID  为空的情况
//     */
//    @Test
//    void testDeleteAllById_EmptyIds() {
//        Set<Long> emptyIds = new HashSet<>();
//
//        // 执行测试
//        ResultVO<Void> result = ordersService.deleteAllById(emptyIds);
//
//        // 验证结果
//        assertEquals(StatusCode.FAIL, result.getCode());
//        assertEquals("ID 不能为空", result.getMessage());
//
//        // 验证数据库操作未被调用
//        verify(orderDetailMapper, never()).delete(any(QueryWrapper.class));
//        verify(ordersMapper, never()).deleteBatchIds(any());
//    }
//
//    /**
//     * 测试更新订单状态 - 更新成功（使用存在的枚举值）
//     */
//    @Test
//    void testUpdateStatus_Success() {
//        // 模拟更新成功
//        when(ordersMapper.updateById(any(OrdersDO.class))).thenReturn(1);
//
//        // 执行测试 - 使用存在的枚举值
//        ResultVO<Void> result = ordersService.updateStatus(1L, OrderStatusEnum.CANCELED);
//
//        // 验证结果
//        assertEquals(StatusCode.SUCCESS, result.getCode());
//
//        // 验证更新方法被调用
//        ArgumentCaptor<OrdersDO> captor = ArgumentCaptor.forClass(OrdersDO.class);
//        verify(ordersMapper).updateById(captor.capture());
//
//        OrdersDO capturedOrder = captor.getValue();
//        assertEquals(1L, capturedOrder.getId());
//        assertEquals(OrderStatusEnum.CANCELED, capturedOrder.getStatus()); // 使用正确的枚举值
//    }
//
//    /**
//     * 测试更新订单状态 - 更新失败
//     */
//    @Test
//    void testUpdateStatus_Failure() {
//        // 模拟更新失败
//        when(ordersMapper.updateById(any(OrdersDO.class))).thenReturn(0);
//
//        // 执行测试
//        ResultVO<Void> result = ordersService.updateStatus(1L, OrderStatusEnum.CANCELED);
//
//        // 验证结果
//        assertEquals(StatusCode.FAIL, result.getCode());
//    }
//
//    /**
//     * 测试定时任务 - 更新订单状态
//     */
//    @Test
//    void testUpdateOrderStatus() {
//        // 准备测试数据：等待发货和等待收货的订单
//        OrdersDO waitDeliverOrder = new OrdersDO();
//        waitDeliverOrder.setId(1L);
//        waitDeliverOrder.setStatus(OrderStatusEnum.WAIT_DELIVER);
//
//        OrdersDO waitReceiveOrder = new OrdersDO();
//        waitReceiveOrder.setId(2L);
//        waitReceiveOrder.setStatus(OrderStatusEnum.WAIT_RECEIVE);
//
//        // 模拟查询返回这些订单
//        when(ordersMapper.selectList(any(QueryWrapper.class)))
//                .thenReturn(Arrays.asList(waitDeliverOrder, waitReceiveOrder));
//
//        // 模拟更新操作
//        when(ordersMapper.updateById(any(OrdersDO.class))).thenReturn(1);
//
//        // 执行定时任务
//        ordersService.updateOrderStatus();
//
//        // 验证查询被调用
//        verify(ordersMapper, times(1)).selectList(any(QueryWrapper.class));
//
//        // 验证更新被调用两次（两个订单都需要更新）
//        verify(ordersMapper, times(2)).updateById(any(OrdersDO.class));
//    }
//
//    /**
//     * 测试定时任务 - 没有待处理的订单
//     */
//    @Test
//    void testUpdateOrderStatus_NoOrders() {
//        // 模拟没有符合条件的订单
//        when(ordersMapper.selectList(any(QueryWrapper.class))).thenReturn(new ArrayList<>());
//
//        // 执行定时任务
//        ordersService.updateOrderStatus();
//
//        // 验证查询被调用，但更新不会被调用
//        verify(ordersMapper, times(1)).selectList(any(QueryWrapper.class));
//        verify(ordersMapper, never()).updateById(any(OrdersDO.class));
//    }
//}
