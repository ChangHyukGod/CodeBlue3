package com.simplecoding.simpledms.service.main;

import com.simplecoding.simpledms.mapper.main.TourMapper;
import com.simplecoding.simpledms.vo.common.Criteria;
import com.simplecoding.simpledms.vo.main.Tour;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        String tourId = UUID.randomUUID().toString();
        String url = generateDownloadUrl(tourId);
        tour.setTourId(tourId);
        tour.setTourFileUrl(url);
        tourMapper.insert(tour);
    }
//  generateDownloadUrl 매서드 정의
    public String generateDownloadUrl(String uuid) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()        // spring 기본주소 : http://localhost:8000
                .path("/api/tour/")              // 추가 경로 넣기 : /advanced/fileDb/
                .path(uuid)                      // uuid 넣기     : xxxxxx
                .toUriString();                  // 합치기 : http://localhost:8000/advanced/fileDb/xxxxxx
    }

//  상세조회
    public Optional<Tour> select(String tourId){
        return tourMapper.select(tourId);
    }

//  수정
    public void update(Tour tour){
        String tourId = tour.getTourId();
        String url = generateDownloadUrl(tourId);
        tour.setTourId(tourId);
        tour.setTourFileUrl(url);
        tourMapper.update(tour);
    }

//  삭제
    public void delete(String tourId){
        tourMapper.delete(tourId);
    }
}