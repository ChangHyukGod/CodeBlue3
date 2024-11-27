package com.simplecoding.simpledms.service.main;

import com.simplecoding.simpledms.mapper.main.TourMapper;
import com.simplecoding.simpledms.vo.common.Criteria;
import com.simplecoding.simpledms.vo.main.Tour;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : KTE
 * @fileName : TourService
 * @since : 24. 11. 27.
 * description :
 */
@Service
@RequiredArgsConstructor
public class TourService {
    private final TourMapper tourMapper;

//  전체조회
    public List<?> selectTour(Criteria searchVO){
        List<?> page = tourMapper.selectTour(searchVO);
//      총건수
        int count = tourMapper.selectTourTotCnt(searchVO);
        searchVO.setTotalItems(count);
        return page;
    }

//  추가, 업로드
    public void insert(Tour tour){
        tourMapper.insert(tour);
    }

//  상세조회
    public Optional<Tour> select(int tourId){
        return tourMapper.select(tourId);
    }
}