package com.fullstack.service.impl;

import com.fullstack.dao.WishlistDao;
import com.fullstack.dao.WishlistProductDao;
import com.fullstack.dto.request.AddorRemoveProductDTO;
import com.fullstack.dto.request.NewWishlistDTO;
import com.fullstack.dto.response.WishlistResponse;
import com.fullstack.exception.WishlistNotFound;
import com.fullstack.external.ProductService;
import com.fullstack.model.Wishlist;
import com.fullstack.model.WishlistProduct;
import com.fullstack.service.WishlistService;
import com.fullstack.vo.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistDao wishlistDao;

    @Autowired
    private WishlistProductDao wishlistProductDao;

    @Autowired
    private ProductService productService;

    @Override
    public WishlistResponse createWishlist(NewWishlistDTO newWishlistDTO) {
        if (wishlistDao.existsByUserId(newWishlistDTO.getUserId())) {
            Wishlist wishlist = wishlistDao.findByUserId(newWishlistDTO.getUserId());

            Set<Product> productHashSet = new HashSet<>();
            wishlist.getWishlistProductSet().forEach(wl -> {
                Product product = productService.getProduct(wl.getProductId());
                productHashSet.add(product);
            });
            return new WishlistResponse(wishlist.getWishListId(), wishlist.getUserId(), productHashSet);
        } else {
            Wishlist wishlist = new Wishlist();
            wishlist.setUserId(newWishlistDTO.getUserId());
            wishlistDao.save(wishlist);

            Set<Product> productHashSet = new HashSet<>();
            wishlist.getWishlistProductSet().forEach(wl -> {
                Product product = productService.getProduct(wl.getProductId());
                productHashSet.add(product);
            });
            return new WishlistResponse(wishlist.getWishListId(), wishlist.getUserId(), productHashSet);
        }
    }

    @Override
    public WishlistResponse addProductsToWishlist(AddorRemoveProductDTO addorRemoveProductDTO) {
        Wishlist wishlist = wishlistDao.findById(addorRemoveProductDTO.getWishListId()).orElseThrow(() ->
                new WishlistNotFound("Wish List Not Found"));

        Product product = productService.getProduct(addorRemoveProductDTO.getProductId());

        WishlistProduct wishlistProduct = new WishlistProduct();
        wishlistProduct.setProductId(product.getProductId());
        wishlistProductDao.save(wishlistProduct);

        wishlist.getWishlistProductSet().add(wishlistProduct);
        wishlistDao.save(wishlist);

        Set<Product> productHashSet = new HashSet<>();
        wishlist.getWishlistProductSet().forEach(wl -> {
            Product product1 = productService.getProduct(wl.getProductId());
            productHashSet.add(product1);
        });
        return new WishlistResponse(wishlist.getWishListId(), wishlist.getUserId(), productHashSet);
    }

    @Override
    public WishlistResponse removeProductFromWishlist(AddorRemoveProductDTO addorRemoveProductDTO) {
        Wishlist wishlist = wishlistDao.findById(addorRemoveProductDTO.getWishListId()).orElseThrow(() ->
                new WishlistNotFound("Wish List Not Found"));

        Product product = productService.getProduct(addorRemoveProductDTO.getProductId());

        WishlistProduct wishlistProduct = wishlistProductDao.findByProductId(product.getProductId());

        wishlist.getWishlistProductSet().remove(wishlistProduct);

        wishlistProductDao.deleteById(wishlistProduct.getId());
        wishlistDao.save(wishlist);

        Set<Product> productHashSet = new HashSet<>();
        wishlist.getWishlistProductSet().forEach(wl -> {
            Product product1 = productService.getProduct(wl.getProductId());
            productHashSet.add(product1);
        });
        return new WishlistResponse(wishlist.getWishListId(), wishlist.getUserId(), productHashSet);
    }

    @Override
    public WishlistResponse getWishListById(String wishListId) {
        Wishlist wishlist = wishlistDao.findById(wishListId).orElseThrow(() -> new WishlistNotFound("Wishlist Not Found !!!!!!!!!"));

        Set<Product> productHashSet = new HashSet<>();
        wishlist.getWishlistProductSet().forEach(ob -> {
            Product product = productService.getProduct(ob.getProductId());
            productHashSet.add(product);
        });
        return new WishlistResponse(wishlist.getWishListId(), wishlist.getUserId(), productHashSet);
    }

    @Override
    public WishlistResponse removeAllProductFromWishlist(String wishListId, List<String> productIds) {
        productIds.forEach(id -> {
            WishlistProduct wishlistProduct = wishlistProductDao.findByProductId(id);
            wishlistProductDao.delete(wishlistProduct);
        });

        Wishlist wishlist = wishlistDao.findById(wishListId).orElseThrow(() -> new WishlistNotFound("Wishlist Not Found !!!!!!!!!"));
        Set<Product> productHashSet = new HashSet<>();
        wishlist.getWishlistProductSet().forEach(ob -> {
            Product product = productService.getProduct(ob.getProductId());
            productHashSet.add(product);
        });
        return new WishlistResponse(wishlist.getWishListId(), wishlist.getUserId(), productHashSet);
    }
}