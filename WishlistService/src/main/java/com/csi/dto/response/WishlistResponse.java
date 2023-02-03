package com.csi.dto.response;

import com.csi.vo.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class WishlistResponse {

    private String wishListId;

    private String userId;

    Set<Product> productList;
}
