package com.fullstack.dao.impl;

import com.fullstack.dao.CouponDao;
import com.fullstack.exception.CouponNotFoundException;
import com.fullstack.model.Coupon;
import com.fullstack.repo.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CouponDaoImpl implements CouponDao {

    @Autowired
    private CouponRepo couponRepo;

    @Override
    public Coupon createCoupon(Coupon coupon) {
        return couponRepo.save(coupon);
    }

    @Override
    public Coupon getCoupon(String couponId) {
        return couponRepo.findById(couponId).orElseThrow(() -> new CouponNotFoundException("Coupon Not Found"));
    }

    @Override
    public Coupon updateCoupon(Coupon coupon) {
        return couponRepo.save(coupon);
    }

    @Override
    public void deleteCoupon(String couponId) {
        Coupon coupon = couponRepo.findById(couponId).orElseThrow(() -> new CouponNotFoundException("Coupon Not Found"));
        couponRepo.delete(coupon);
    }

    @Override
    public List<Coupon> getAllCoupon() {
        return couponRepo.findAll();
    }

    @Override
    public Coupon getByName(String couponName) {
        return couponRepo.findByCouponName(couponName);
    }
}
