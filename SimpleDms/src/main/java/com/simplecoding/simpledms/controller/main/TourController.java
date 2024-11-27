package com.simplecoding.simpledms.controller.main;

import com.simplecoding.simpledms.service.main.TourService;
import com.simplecoding.simpledms.vo.common.Criteria;
import com.simplecoding.simpledms.vo.dto.ResultDto;
import com.simplecoding.simpledms.vo.main.Tour;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author : KTE
 * @fileName : TourController
 * @since : 24. 11. 27.
 * description :
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class TourController {
    private final TourService tourService;

//  전체조회
    @GetMapping("/api/tour")
    public ResponseEntity<?> selectTour(Criteria searchVO) {
        List<?> tours = tourService.selectTour(searchVO);
//      총건수
        ResultDto resultDto = new ResultDto(tours, searchVO.getTotalItems());
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

//  추가, 업로드
    @PostMapping("/api/tour/add")
    public ResponseEntity<?> insert(@RequestBody Tour tour) {
        tourService.insert(tour);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//  상세조회
    @GetMapping("/api/tour/get/{tourId}")
    public ResponseEntity<?> select(@PathVariable int tourId) {
        Optional<Tour> tour = tourService.select(tourId);
//      에러처리
        if (tour.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tour.get(), HttpStatus.OK);
    }
}