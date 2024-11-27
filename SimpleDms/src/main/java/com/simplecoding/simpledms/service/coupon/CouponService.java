package com.simplecoding.simpledms.service.coupon;

import com.simplecoding.simpledms.mapper.coupon.CouponMapper;
import com.simplecoding.simpledms.vo.coupon.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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


}
