package com.simplecoding.simpledms.mapper.main;

import com.simplecoding.simpledms.vo.common.Criteria;
import com.simplecoding.simpledms.vo.main.Tour;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
 * @author : KTE
 * @fileName : TourMapper
 * @since : 24. 11. 27.
 * description :
 */
@Mapper
public interface TourMapper {
    public List<?> selectTour(Criteria searchVO);  //전체조회
    public int selectTourTotCnt(Criteria searchVO);  //총건수
    public int insert(Tour tour);  //추가, 업로드
    public Optional<Tour> select(String tourId);  //상세조회
    public int update(Tour tour);  //수정
    public int delete(String tourId);  //삭제
}