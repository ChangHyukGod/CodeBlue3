package com.simplecoding.simpledms.controller.Coupon;

import com.simplecoding.simpledms.service.coupon.CouponService;
import com.simplecoding.simpledms.vo.coupon.Coupon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/api/coupon/coupon")
    public ResponseEntity<?> insert(
            @RequestBody Coupon coupon
    ) {
        couponService.insert(coupon);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}






