package com.csi.controller;

import com.csi.model.Coupon;
import com.csi.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon")
@CrossOrigin(origins = "http://localhost:8000", allowCredentials = "true")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/create")
    public ResponseEntity<?> createCoupon(@RequestBody Coupon coupon) {
        return new ResponseEntity<>(couponService.createCoupon(coupon), HttpStatus.OK);
    }

    @GetMapping("/get/{couponId}")
    public ResponseEntity<?> getCoupon(@PathVariable String couponId) {
        return new ResponseEntity<>(couponService.getCoupon(couponId), HttpStatus.OK);
    }

    @PutMapping("/update/{couponId}")
    public ResponseEntity<?> updateCoupon(@PathVariable String couponId, @RequestBody Coupon coupon) {
        Coupon coupon1 = couponService.getCoupon(couponId);
        coupon1.setCouponName(coupon.getCouponName());
        coupon1.setDescription(coupon.getDescription());
        coupon1.setDiscount(coupon.getDiscount());
        return new ResponseEntity<>(couponService.updateCoupon(coupon1), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{couponId}")
    public ResponseEntity<?> deleteCoupon(@PathVariable String couponId) {
        couponService.deleteCoupon(couponId);
        return new ResponseEntity<>("Coupon Deleted Successfully ", HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCoupon() {
        return new ResponseEntity<>(couponService.getAllCoupon(), HttpStatus.OK);
    }
}