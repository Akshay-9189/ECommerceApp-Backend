package com.csi.controller;

import com.csi.dto.request.AddorRemoveProductDTO;
import com.csi.dto.request.NewWishlistDTO;
import com.csi.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin(origins = "http://localhost:8000", allowCredentials = "true")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/save")
    public ResponseEntity<?> createNewWishList(@RequestBody NewWishlistDTO newWishlistDTO) {
        return new ResponseEntity<>(wishlistService.createWishlist(newWishlistDTO), HttpStatus.OK);
    }

    @PostMapping("/add-product")
    public ResponseEntity<?> addProductToWishlist(@RequestBody AddorRemoveProductDTO addorRemoveProductDTO) {
        return new ResponseEntity<>(wishlistService.addProductsToWishlist(addorRemoveProductDTO), HttpStatus.OK);
    }

    @PostMapping("/remove-product")
    public ResponseEntity<?> removeProductFromWishlist(@RequestBody AddorRemoveProductDTO addorRemoveProductDTO) {
        return new ResponseEntity<>(wishlistService.removeProductFromWishlist(addorRemoveProductDTO), HttpStatus.OK);
    }

    @GetMapping("/get/{wishListId}")
    public ResponseEntity<?> getWishlistById(@PathVariable String wishListId) {
        return new ResponseEntity<>(wishlistService.getWishListById(wishListId), HttpStatus.OK);
    }

    @DeleteMapping("/remove-all/{wishListId}/{productIds}")
    public ResponseEntity<?> removeAllProductFromWishList(@PathVariable String wishListId, @PathVariable List<String> productIds) {
        return new ResponseEntity<>(wishlistService.removeAllProductFromWishlist(wishListId, productIds), HttpStatus.OK);
    }
}
