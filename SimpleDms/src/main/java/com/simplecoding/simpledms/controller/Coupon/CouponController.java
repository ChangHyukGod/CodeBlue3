package com.simplecoding.simpledms.controller.Coupon;

import com.simplecoding.simpledms.service.coupon.CouponService;
import com.simplecoding.simpledms.vo.coupon.Coupon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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








}






