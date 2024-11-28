package com.simplecoding.simpledms.controller.review;

import com.simplecoding.simpledms.service.review.ReviewService;
import com.simplecoding.simpledms.vo.common.Criteria;
import com.simplecoding.simpledms.vo.dto.ResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : KTE
 * @fileName : ReviewController
 * @since : 24. 11. 27.
 * description :
 */
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 전체 조회
    @GetMapping("/api/review/review")
    public ResponseEntity<?> selectReviewListTotCnt(Criteria searchVO) {
        List<?> reviews = reviewService.selectReviewList(searchVO);
        ResultDto resultDto
                = new ResultDto(reviews, searchVO.getTotalItems());
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }
}
