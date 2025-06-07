package com.fullstack.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Coupon {

    @Id
    @GeneratedValue(generator = "randomUUID3")
    @GenericGenerator(name = "randomUUID3", strategy = "com.fullstack.util.UUIDGenerator")
    private String couponId;

    private String couponName;

    private double discount;

    private String description;
}
