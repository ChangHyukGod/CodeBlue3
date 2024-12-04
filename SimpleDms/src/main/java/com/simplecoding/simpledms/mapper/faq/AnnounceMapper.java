package com.simplecoding.simpledms.mapper.faq;

import com.simplecoding.simpledms.vo.common.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : KTE
 * @fileName : AnnounceMapper
 * @since : 24. 12. 3.
 * description :
 */
@Mapper
public interface AnnounceMapper {
    List<?> selectAno(Criteria searchVO);
    int selectAnoCount(Criteria searchVO);
}
