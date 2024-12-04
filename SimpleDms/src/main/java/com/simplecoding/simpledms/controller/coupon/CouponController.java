package com.simplecoding.simpledms.controller.coupon;

import com.simplecoding.simpledms.service.coupon.CouponService;
import com.simplecoding.simpledms.vo.common.Criteria;
import com.simplecoding.simpledms.vo.coupon.Coupon;
import com.simplecoding.simpledms.vo.dto.ResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;



/**
 * @author : KTE-149
 * @fileName : CouponController
 * @since : 24. 11. 26.
 * description :
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;

    // 쿠폰생성

//    @PostMapping("/api/coupon/coupon")
//    public ResponseEntity<?> insert(
//            @RequestBody Coupon coupon
//    ) {
//        couponService.insert(coupon);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @PostMapping("/api/coupon/coupon")
    public ResponseEntity<?> insert(
            @RequestBody Coupon coupon,
            Authentication authentication // Spring Security의 Authentication 객체 주입
    ) {
        // 현재 인증된 사용자의 이메일 추출
        String email = authentication.getName();

        // Coupon 객체에 이메일 설정
        coupon.setMemberEmail(email);

        couponService.insert(coupon);
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    @PostMapping("/api/coupon/coupon")
//    public ResponseEntity<?> insert(@RequestBody Coupon coupon,
//                                    @RequestParam double totalPrice,
//                                    @RequestParam String memberEmail) {
//        couponService.insert(coupon, totalPrice, memberEmail);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


    @GetMapping("/api/coupon/coupon")
    public ResponseEntity<?> selectCouponList(Criteria searchVO) {
        log.info("테스트");
        List<?> coupons = couponService.selectCouponList(searchVO);
//      TODO: depts(배열) 1개 + 총건수 1개 -> 박스포장용 객체
//          객체생성(택배박스: DTO 객체) : 속성필드 (배열), 속성필드(총건수)
        ResultDto resultDto
                = new ResultDto(coupons, searchVO.getTotalItems());
//      TODO: 상태코드 : HttpStatus.OK, NO_CONTENT ...
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }










}






