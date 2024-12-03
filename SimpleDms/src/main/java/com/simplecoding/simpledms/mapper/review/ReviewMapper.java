package com.simplecoding.simpledms.mapper.review;

import com.simplecoding.simpledms.vo.common.Criteria;
import com.simplecoding.simpledms.vo.review.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : KTE
 * @fileName : ReviewMapper
 * @since : 24. 11. 27.
 * description :
 */
@Mapper
public interface ReviewMapper {
    public List<?> selectReviewList(Criteria searchVO);
    public int selectReviewListTotCnt(Criteria searchVO);
    
}
