package com.simplecoding.simpledms.mapper.coupon;

import com.simplecoding.simpledms.vo.coupon.Coupon;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : KTE-149
 * @fileName : CouponMapper
 * @since : 24. 11. 26.
 * description :
 */
@Mapper
public interface CouponMapper {

    int insert(Coupon coupon);  // 쿠폰 insert 메서드



}
