package com.csi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CartItems {
    private String cartItemsId;

    private String productId;

    private int productSelectedQuantity;

    private long totalPrice;
}
