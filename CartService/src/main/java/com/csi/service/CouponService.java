package com.csi.service;

import com.csi.exception.CouponNotFoundException;
import com.csi.model.Coupon;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface CouponService {
    Coupon createCoupon(Coupon coupon);

    @Cacheable(value = "couponId")
    Coupon getCoupon(String couponId);

    Coupon updateCoupon(Coupon coupon);

    void deleteCoupon(String couponId);

    List<Coupon> getAllCoupon();

    Coupon getByName(String couponName);
}
