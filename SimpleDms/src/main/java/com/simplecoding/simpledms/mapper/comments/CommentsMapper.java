package com.simplecoding.simpledms.mapper.comments;

import com.simplecoding.simpledms.vo.comments.Comments;
import com.simplecoding.simpledms.vo.common.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : KTE-149
 * @fileName : CommentsMapper
 * @since : 24. 12. 3.
 * description :
 */
@Mapper
public interface CommentsMapper {


    public List<?> selectCommentsList(Criteria searchVO);    // 전체조회
    public int selectCommentsListTotCnt(Criteria searchVO); // 총건수
    public int insert(Comments comments);                       // 부서생성




}
