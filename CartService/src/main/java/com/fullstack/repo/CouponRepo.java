package com.fullstack.repo;

import com.fullstack.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CouponRepo extends JpaRepository<Coupon, String> {

    Coupon findByCouponName(String couponName);
}
