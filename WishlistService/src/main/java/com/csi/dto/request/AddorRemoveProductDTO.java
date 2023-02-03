package com.csi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AddorRemoveProductDTO {
    private String wishListId;
    private String productId;
}
