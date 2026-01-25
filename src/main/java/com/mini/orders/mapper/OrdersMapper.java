package com.mini.orders.mapper;

import com.mini.orders.domain.OrdersDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author 19256
* @description 针对表【orders(订单表)】的数据库操作Mapper
* @createDate 2025-04-20 04:00:57
* @Entity com.mini.orders.domain.Orders
*/
@Mapper
public interface OrdersMapper extends BaseMapper<OrdersDO> {
    OrdersDO selectByOrderNo(@Param("orderNo") String orderNo);
}




