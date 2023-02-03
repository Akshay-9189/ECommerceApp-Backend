package com.csi.repo;

import com.csi.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CouponRepo extends JpaRepository<Coupon, String> {

    Coupon findByCouponName(String couponName);
}
