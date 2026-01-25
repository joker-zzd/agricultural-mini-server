package com.mini.reviews.service;

import com.mini.resultvo.ResultVO;
import com.mini.reviews.domain.ReviewsDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mini.reviews.domain.dto.AddReviewDTO;
import com.mini.reviews.domain.vo.ReviewsListVO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 19256
 * @description 针对表【reviews(评价表)】的数据库操作Service
 * @createDate 2025-04-16 11:01:08
 */
public interface ReviewsService extends IService<ReviewsDO> {
    ResultVO<List<ReviewsListVO>> findByPageAndProductId(Integer currentPage, Integer pageSize, @NotEmpty(message = "id不能为空") Long productId);
    ResultVO<Void> addReview(AddReviewDTO addReviewDTO);
}
