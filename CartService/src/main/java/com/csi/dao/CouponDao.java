package com.csi.dao;

import com.csi.exception.CouponNotFoundException;
import com.csi.model.Coupon;

import java.util.List;

public interface CouponDao {

    Coupon createCoupon(Coupon coupon);

    Coupon getCoupon(String couponId);

    Coupon updateCoupon(Coupon coupon);

    void deleteCoupon(String couponId);

    List<Coupon> getAllCoupon();

    Coupon getByName(String couponName);
}
