package com.mini.orderdetail.mapper;

import com.mini.orderdetail.domain.OrderDetailDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
* @author 19256
* @description 针对表【order_detail(订单详情表)】的数据库操作Mapper
* @createDate 2025-05-19 02:01:09
* @Entity com.mini.orderdetail.domain.OrderDetail
*/
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetailDO> {
    @Update("UPDATE order_detail SET commented = TRUE " +
            "WHERE order_id = #{orderId} AND product_id = #{productId}")
    void updateCommentedStatus(@Param("orderId") Long orderId, @Param("productId") Long productId);


}




