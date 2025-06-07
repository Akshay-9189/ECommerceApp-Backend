package com.fullstack.service.impl;

import com.fullstack.dao.CouponDao;
import com.fullstack.model.Coupon;
import com.fullstack.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponDao couponDao;

    @Override
    public Coupon createCoupon(Coupon coupon) {
        return couponDao.createCoupon(coupon);
    }

    @Override
    public Coupon getCoupon(String couponId) {
        return couponDao.getCoupon(couponId);
    }

    @Override
    public Coupon updateCoupon(Coupon coupon) {
        return couponDao.updateCoupon(coupon);
    }

    @Override
    public void deleteCoupon(String couponId) {
        couponDao.deleteCoupon(couponId);
    }

    @Override
    public List<Coupon> getAllCoupon() {
        return couponDao.getAllCoupon();
    }

    @Override
    public Coupon getByName(String couponName) {
        return couponDao.getByName(couponName);
    }
}
