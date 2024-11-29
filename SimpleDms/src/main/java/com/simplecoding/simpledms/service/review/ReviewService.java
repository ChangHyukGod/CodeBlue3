package com.simplecoding.simpledms.service.review;

import com.simplecoding.simpledms.mapper.review.ReviewMapper;
import com.simplecoding.simpledms.vo.common.Criteria;
import com.simplecoding.simpledms.vo.review.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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


}
