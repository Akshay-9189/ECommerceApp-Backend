package com.csi.service;

import com.csi.dto.request.AddorRemoveProductDTO;
import com.csi.dto.request.NewWishlistDTO;
import com.csi.dto.response.WishlistResponse;

import java.util.List;


public interface WishlistService {

    WishlistResponse createWishlist(NewWishlistDTO newWishlistDTO);

    WishlistResponse addProductsToWishlist(AddorRemoveProductDTO addorRemoveProductDTO);

    WishlistResponse removeProductFromWishlist(AddorRemoveProductDTO addorRemoveProductDTO);

    WishlistResponse getWishListById(String wishListId);

    WishlistResponse removeAllProductFromWishlist(String wishListId ,List<String> productIds);
}
