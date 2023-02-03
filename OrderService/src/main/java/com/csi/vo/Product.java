package com.csi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product {

    private String productId;

    private String productName;

    private double productWeight;

    private String productDescription;

    private String productCode;

    private int productPrice;

    private int productTotalQuantityAvailable;

    private String imageURL;
}
