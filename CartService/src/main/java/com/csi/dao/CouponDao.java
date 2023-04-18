package com.csi.dao;

import com.csi.model.Coupon;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface CouponDao {

    @CacheEvict(value = {"CouponByID", "Coupons", "CouponByName", "ShoppingCardByID", "ShoppingCartByUserID"}, allEntries = true)
    Coupon createCoupon(Coupon coupon);

    @Cacheable(value = "CouponByID")
    Coupon getCoupon(String couponId);

    @CacheEvict(value = {"CouponByID", "Coupons", "CouponByName", "ShoppingCardByID", "ShoppingCartByUserID"}, allEntries = true)
    Coupon updateCoupon(Coupon coupon);

    @CacheEvict(value = {"CouponByID", "Coupons", "CouponByName", "ShoppingCardByID", "ShoppingCartByUserID"}, allEntries = true)
    void deleteCoupon(String couponId);

    @Cacheable(value = "Coupons")
    List<Coupon> getAllCoupon();

    @Cacheable(value = "CouponByName")
    Coupon getByName(String couponName);
}