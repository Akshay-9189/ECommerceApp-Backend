package com.csi.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveProductDTO {
    private String productName;

    private double productWeight;

    private String productDescription;

    private String productCode;

    private int productPrice;

    private int productTotalQuantityAvailable;

    private String subCategoryName;
}
