package com.csi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ShoppingCart {

    private String shoppingCartId;

    private String userId;

    private Set<CartItems> cartItems = new HashSet<>();

    private long grandTotalBeforeDiscount;

    private Coupon coupon;

    private long grandTotalAfterDiscount;

    private long savedAmount;
}
