package com.simplecoding.simpledms.service.review;

import com.simplecoding.simpledms.mapper.review.ReviewMapper;
import com.simplecoding.simpledms.vo.common.Criteria;
import com.simplecoding.simpledms.vo.review.Review;
import jakarta.servlet.Servlet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

/**
 * @author : KTE
 * @fileName : ReviewService
 * @since : 24. 11. 27.
 * description :
 */
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewMapper reviewMapper;

    //    전체조회 + 총건수 추가
    public List<?> selectReviewList(Criteria searchVO) {
        List<?> page = reviewMapper.selectReviewList(searchVO);
        //        TODO: 총건수 저장 : Criteria 의 totalItems
        int count = reviewMapper.selectReviewListTotCnt(searchVO);
        searchVO.setTotalItems(count);
        return page;
    }

    //    리뷰추가
    public void insert(Review review) {
        reviewMapper.insert(review);

        int reviewId = reviewMapper.selectGenerateReviewId();
        review.setReviewId(reviewId);
        String url = generateImageUrl(review.getReviewId());
        review.setImageUrl(url);
        reviewMapper.updateReviewUrl(review);

    }

    public String generateImageUrl(int reviewId) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/review/")
                .path(String.valueOf(reviewId))
                .toUriString();
    }

    //    상세조회
    public Optional<Review> selectReview(int reviewId ) {return reviewMapper.selectReview(reviewId); }

    //    리뷰수정
    public void update(Review review) {
        reviewMapper.update(review);
    }

    //    리뷰삭제
    public void delete(int reviewId) {
        reviewMapper.delete(reviewId);
    }

}
