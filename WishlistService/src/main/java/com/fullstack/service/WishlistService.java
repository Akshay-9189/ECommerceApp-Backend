package com.fullstack.service;

import com.fullstack.dto.request.AddorRemoveProductDTO;
import com.fullstack.dto.request.NewWishlistDTO;
import com.fullstack.dto.response.WishlistResponse;

import java.util.List;


public interface WishlistService {

    WishlistResponse createWishlist(NewWishlistDTO newWishlistDTO);

    WishlistResponse addProductsToWishlist(AddorRemoveProductDTO addorRemoveProductDTO);

    WishlistResponse removeProductFromWishlist(AddorRemoveProductDTO addorRemoveProductDTO);

    WishlistResponse getWishListById(String wishListId);

    WishlistResponse removeAllProductFromWishlist(String wishListId ,List<String> productIds);
}
