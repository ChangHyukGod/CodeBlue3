package com.simplecoding.simpledms.service.coupon;

import com.simplecoding.simpledms.mapper.coupon.CouponMapper;
import com.simplecoding.simpledms.vo.common.Criteria;
import com.simplecoding.simpledms.vo.coupon.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : KTE-149
 * @fileName : CouponService
 * @since : 24. 11. 26.
 * description :
 */
@Service
@RequiredArgsConstructor

public class CouponService {
    private final CouponMapper couponMapper;


    public void insert(Coupon coupon) {

        couponMapper.insert(coupon);
    }            // 쿠폰인서트



//    public void insert(Coupon coupon, double value, String me) {
//        // 20% 할인 금액 계산
//        double discountAmount = totalPrice * 0.20; // 할인 금액 계산
//
//        // 쿠폰에 할인 금액과 이메일 값 설정
//        coupon.setValue(discountAmount);  // 할인 금액
//        coupon.setMemberEmail(memberEmail);  // 이메일 값 설정
//
//        // 쿠폰 삽입
//        couponMapper.insert(coupon);
//    }


    //    TODO: 전체조회 + 총건수(Criteria 의 totalItems 변수 저장)
    public List<?> selectCouponList(Criteria searchVO) {
        List<?> page = couponMapper.selectCouponList(searchVO);

//        TODO: 총건수 저장 : Criteria 의 totalItems
        int count = couponMapper.selectCouponListTotCnt(searchVO);
        searchVO.setTotalItems(count);

        return page;
    }












}
