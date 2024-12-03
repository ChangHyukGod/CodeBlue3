package com.simplecoding.simpledms.service.comments;

import com.simplecoding.simpledms.mapper.comments.CommentsMapper;
import com.simplecoding.simpledms.vo.comments.Comments;
import com.simplecoding.simpledms.vo.common.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : KTE-149
 * @fileName : CommentsService
 * @since : 24. 12. 3.
 * description :
 */
@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsMapper commentsMapper;


    //    전체조회 + 총건수 추가
    public List<?> selectCommentsList(Criteria searchVO) {
        List<?> page = commentsMapper.selectCommentsList(searchVO);
        //        TODO: 총건수 저장 : Criteria 의 totalItems
        int count = commentsMapper.selectCommentsListTotCnt(searchVO);
        searchVO.setTotalItems(count);
        return page;
    }

    public void insert(Comments comments) {
        commentsMapper.insert(comments);
    }



}
