package com.simplecoding.simpledms.mapper.faq;

import com.simplecoding.simpledms.vo.faq.Faq;
import com.simplecoding.simpledms.vo.common.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : KTE
 * @fileName : FaqMapper
 * @since : 24. 12. 2.
 * description :
 */
@Mapper
public interface FaqMapper {
    List<?> selectFaqList(Criteria searchVO);
    int selectFaqListTotCount(Criteria searchVO);
    int insertFaq(Faq faq);
}
