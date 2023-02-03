package com.csi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Coupon {

    private String couponId;

    private String couponName;

    private double discount;

    private String description;
}
