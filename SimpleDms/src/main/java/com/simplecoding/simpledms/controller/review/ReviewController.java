package com.simplecoding.simpledms.controller.review;

import com.simplecoding.simpledms.service.review.ReviewService;
import com.simplecoding.simpledms.vo.common.Criteria;
import com.simplecoding.simpledms.vo.dto.ResultDto;
import com.simplecoding.simpledms.vo.review.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    // 리뷰 추가
    @PostMapping("/api/review/review/add")
    public ResponseEntity<?> insertReview(@RequestBody Review review) {
        reviewService.insert(review);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 상세조회
    @GetMapping("/api/review/review/{reviewId}")
    public ResponseEntity<?> selectReview(
            @PathVariable int reviewId
    ) {
        Optional<Review> review = reviewService.selectReview(reviewId);
        if (review.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(review.get(), HttpStatus.OK);
    }

    // 리뷰 수정
    @PutMapping("/api/review/review/update/{reviewId}")
    public ResponseEntity<?> updateReview(
            @PathVariable int reviewId,
            @RequestBody Review review
    ) {
        review.setReviewId(reviewId); // 요청된 ID로 review 객체 업데이트
        reviewService.update(review);
        return new ResponseEntity<>(HttpStatus.OK); // 200 OK
    }

    // 리뷰 삭제
    @DeleteMapping("/api/review/review/deletion/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable int reviewId) {
        reviewService.delete(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
}
