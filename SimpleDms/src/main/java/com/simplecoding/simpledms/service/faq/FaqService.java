package com.simplecoding.simpledms.service.faq;

import com.simplecoding.simpledms.mapper.faq.FaqMapper;
import com.simplecoding.simpledms.vo.common.Criteria;
import com.simplecoding.simpledms.vo.faq.Faq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : KTE
 * @fileName : FaqService
 * @since : 24. 12. 2.
 * description :
 */
@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqMapper faqMapper;

    public List<?> selectFaqList(Criteria searchVO) {
        List<?> page = faqMapper.selectFaqList(searchVO);
        int count = faqMapper.selectFaqListTotCount(searchVO);
        searchVO.setTotalItems(count);
        return page;
    }

    public void insertFaq(Faq faq) {
        faqMapper.insertFaq(faq);
    }
}