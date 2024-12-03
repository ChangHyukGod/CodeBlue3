package com.simplecoding.simpledms.controller.faq;

import com.simplecoding.simpledms.service.faq.AnnounceService;
import com.simplecoding.simpledms.vo.common.Criteria;
import com.simplecoding.simpledms.vo.dto.ResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : KTE
 * @fileName : AnnounceController
 * @since : 24. 12. 3.
 * description :
 */
@RestController
@RequiredArgsConstructor
public class AnnounceController {

    private final AnnounceService announceService;

    @GetMapping("/api/announcement")
    public ResponseEntity<?> selectAnouncement(Criteria searchVO) {
        List<?> announces = announceService.selectAnoList(searchVO);
        ResultDto resultDto = new ResultDto(announces, searchVO.getTotalItems());
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }
}
