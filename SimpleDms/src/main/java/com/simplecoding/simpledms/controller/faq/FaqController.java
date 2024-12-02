package com.simplecoding.simpledms.controller.faq;

import com.simplecoding.simpledms.service.faq.FaqService;
import com.simplecoding.simpledms.vo.common.Criteria;
import com.simplecoding.simpledms.vo.dto.ResultDto;
import com.simplecoding.simpledms.vo.faq.Faq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : KTE
 * @fileName : FaqController
 * @since : 24. 12. 2.
 * description :
 */
@RestController
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;

    @GetMapping("/api/faq/list")
    public ResponseEntity<?> selectFaqList(Criteria searchVO) {
        List<?> faqs = faqService.selectFaqList(searchVO);
        ResultDto resultDto = new ResultDto(faqs, searchVO.getTotalItems());
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @PostMapping("/api/faq/list")
    public ResponseEntity<?> insertFaq(@RequestBody Faq faq) {
        faqService.insertFaq(faq);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

