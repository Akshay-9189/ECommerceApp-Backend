package com.fullstack.dto.response;

import com.fullstack.vo.Product;
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
