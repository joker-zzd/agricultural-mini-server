package com.mini.reviews.controller;

import com.mini.product.domain.vo.ProductListVO;
import com.mini.resultvo.ResultVO;
import com.mini.reviews.domain.dto.AddReviewDTO;
import com.mini.reviews.domain.vo.ReviewsListVO;
import com.mini.reviews.service.ReviewsService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/reviews")
public class ReviewsController {
    private final ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @GetMapping("/findByPageAndProductId")
    public ResultVO<List<ReviewsListVO>> findByPageAndProductId(
            @RequestParam(name = "currentPage", defaultValue = "1", required = false) Integer currentPage,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(name = "productId", required = false) Long productId
    ) {
        return this.reviewsService.findByPageAndProductId(currentPage, pageSize,productId);
    }

    @PostMapping("/add")
    public ResultVO<Void> addReview(@RequestBody AddReviewDTO addReviewDTO){
        return this.reviewsService.addReview(addReviewDTO);
    }
}
