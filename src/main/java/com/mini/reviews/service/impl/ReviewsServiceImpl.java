package com.mini.reviews.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mini.businessuser.domain.UserDO;
import com.mini.businessuser.mapper.UserMapper;
import com.mini.filter.LoginUser;
import com.mini.orderdetail.mapper.OrderDetailMapper;
import com.mini.resultvo.ResultVO;
import com.mini.reviews.domain.ReviewsDO;
import com.mini.reviews.domain.dto.AddReviewDTO;
import com.mini.reviews.domain.vo.ReviewsListVO;
import com.mini.reviews.service.ReviewsService;
import com.mini.reviews.mapper.ReviewsMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 19256
 * @description 针对表【reviews(评价表)】的数据库操作Service实现
 * @createDate 2025-04-16 11:01:08
 */
@Service
public class ReviewsServiceImpl extends ServiceImpl<ReviewsMapper, ReviewsDO>
        implements ReviewsService {
    private final ReviewsMapper reviewsMapper;
    private final UserMapper userMapper;
    private final OrderDetailMapper orderDetailMapper;

    public ReviewsServiceImpl(ReviewsMapper reviewsMapper,
                              UserMapper userMapper,
                              OrderDetailMapper orderDetailMapper) {
        this.reviewsMapper = reviewsMapper;
        this.userMapper = userMapper;
        this.orderDetailMapper=orderDetailMapper;
    }


    @Override
    public ResultVO<List<ReviewsListVO>> findByPageAndProductId(Integer currentPage, Integer pageSize, Long productId) {
        Page<ReviewsDO> page = new Page<>(currentPage, pageSize);

        LambdaQueryWrapper<ReviewsDO> wrapper = new LambdaQueryWrapper<ReviewsDO>()
                .eq(ReviewsDO::getProductId, productId)
                .orderByDesc(ReviewsDO::getCreatedAt);

        Page<ReviewsDO> resultPage = reviewsMapper.selectPage(page, wrapper);

        List<ReviewsListVO> voList = resultPage.getRecords().stream().map(reviewsDO -> {
            ReviewsListVO vo = new ReviewsListVO();
            vo.setId(reviewsDO.getId());
            vo.setRating(reviewsDO.getRating());
            vo.setComment(reviewsDO.getComment());
            vo.setCreatedAt(reviewsDO.getCreatedAt());

            // 查询评论者昵称（如果需要）
            UserDO user = userMapper.selectById(reviewsDO.getUserId());
            vo.setNickname(user != null ? user.getNickname() : "匿名用户");

            return vo;
        }).collect(Collectors.toList());

        return ResultVO.success(voList);
    }

    @Override
    @Transactional
    public ResultVO<Void> addReview(AddReviewDTO addReviewDTO) {
        ReviewsDO reviewsDO=new ReviewsDO();

        reviewsDO.setProductId(addReviewDTO.getProductId());
        reviewsDO.setUserId(31L);
        reviewsDO.setRating(addReviewDTO.getRating());
        reviewsDO.setComment(addReviewDTO.getComment());

       boolean result= this.reviewsMapper.insert(reviewsDO)>0;

       if (result){
            orderDetailMapper.updateCommentedStatus(addReviewDTO.getOrderId(), addReviewDTO.getProductId());
           return ResultVO.success();
       }else{
           return ResultVO.fail();
       }
    }

}




