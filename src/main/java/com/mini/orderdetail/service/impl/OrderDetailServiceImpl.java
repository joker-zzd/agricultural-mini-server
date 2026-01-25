package com.mini.orderdetail.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mini.feedback.domain.dto.FeedbackDTO;
import com.mini.orderdetail.domain.OrderDetailDO;
import com.mini.orderdetail.service.OrderDetailService;
import com.mini.orderdetail.mapper.OrderDetailMapper;
import com.mini.resultvo.ResultVO;
import org.springframework.stereotype.Service;

/**
* @author 19256
* @description 针对表【order_detail(订单详情表)】的数据库操作Service实现
* @createDate 2025-05-19 02:01:09
*/
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetailDO>
    implements OrderDetailService{

}




