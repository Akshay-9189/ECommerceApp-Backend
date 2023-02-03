package com.csi.service.impl;

import com.csi.dao.CartItemsDao;
import com.csi.dao.ShoppingCartDao;
import com.csi.exception.CartItemNotFound;
import com.csi.exception.ProductAlreadyExists;
import com.csi.model.CartItems;
import com.csi.model.Coupon;
import com.csi.model.ShoppingCart;
import com.csi.service.CouponService;
import com.csi.service.ShoppingCartService;
import com.csi.vo.Product;
import com.csi.vo.UserData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private CartItemsDao cartItemsDao;

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CouponService couponService;

    @Override
    public ShoppingCart createShoppingCart(String userId) {
        UserData userData = restTemplate.getForObject("http://UserService/user/get/" + userId, UserData.class);
        ShoppingCart shoppingCart = new ShoppingCart();
        if (userData != null) {
            shoppingCart.setUserId(userData.getUserId());
        }
        return shoppingCartDao.saveShoppingCart(shoppingCart);
    }

    @Override
    public ShoppingCart addProductToCart(String shoppingCartId, String productId) {
        Product product = restTemplate.getForObject("http://ProductService/product/get/" + productId, Product.class);
        ShoppingCart shoppingCart = shoppingCartDao.getCartById(shoppingCartId);

        // If No Products In Cart Then Add New Product To Cart
        if (shoppingCart.getCartItems().stream().noneMatch(prodId -> prodId.getProductId().equals(productId))) {
            ShoppingCart shoppingCart1 = shoppingCartDao.getCartById(shoppingCartId);
            CartItems cartItems1 = new CartItems();
            if (product != null) {
                cartItems1.setProductId(product.getProductId());
                cartItems1.setProductSelectedQuantity(cartItems1.getProductSelectedQuantity() + 1);
                cartItems1.setTotalPrice((long) product.getProductPrice() * cartItems1.getProductSelectedQuantity());
                cartItemsDao.saveCartItem(cartItems1);
            }
            shoppingCart1.getCartItems().add(cartItems1);
            shoppingCart1.setGrandTotalBeforeDiscount(shoppingCart1.getCartItems().stream().map(CartItems::getTotalPrice).reduce(0L, Long::sum));

            // If Cart Having Coupon Then Update grandTotalAfterDiscount , savedAmount
            if (shoppingCart.getCoupon() != null) {
                double discount = (shoppingCart.getGrandTotalBeforeDiscount() * shoppingCart.getCoupon().getDiscount()) / 100;
                shoppingCart.setGrandTotalAfterDiscount(Math.round(shoppingCart.getGrandTotalBeforeDiscount() - discount));
                shoppingCart.setSavedAmount(Math.round(shoppingCart.getGrandTotalBeforeDiscount() - shoppingCart.getGrandTotalAfterDiscount()));
            } else {
                // If Coupon Not Present In Cart Then Set grandTotalAfterDiscount , savedAmount As 0
                shoppingCart.setGrandTotalAfterDiscount(0);
                shoppingCart.setSavedAmount(0);
            }
//            shoppingCart.getCartItems().stream().sorted(Comparator.comparing(CartItems::getCartItemsId)).close();
            return shoppingCartDao.saveShoppingCart(shoppingCart1);
        } else {
            throw new ProductAlreadyExists("Product Already Exists In Cart");
        }
    }

    @Override
    public ShoppingCart increaseProductQuantityFromCart(String shoppingCartId, String productId) {
        Product product = restTemplate.getForObject("http://ProductService/product/get/" + productId, Product.class);
        ShoppingCart shoppingCart = shoppingCartDao.getCartById(shoppingCartId);

        // If Already product In Cart Then Increase Quantity By 1
        if (product != null) {
            if (shoppingCart.getCartItems().stream().anyMatch(id -> id.getProductId().equals(product.getProductId()))) {

                shoppingCart.getCartItems().stream().filter(id -> id.getProductId().equals(product.getProductId())).forEach(item -> {
                    item.setProductSelectedQuantity(item.getProductSelectedQuantity() + 1);
                    item.setTotalPrice(item.getTotalPrice() + product.getProductPrice());
                    cartItemsDao.saveCartItem(item);
                });
                shoppingCart.setGrandTotalBeforeDiscount(shoppingCart.getCartItems().stream().map(CartItems::getTotalPrice).reduce(0L, Long::sum));

                // If Cart Having Coupon Then Update grandTotalAfterDiscount , savedAmount
                if (shoppingCart.getCoupon() != null) {
                    double discount = (shoppingCart.getGrandTotalBeforeDiscount() * shoppingCart.getCoupon().getDiscount()) / 100;
                    shoppingCart.setGrandTotalAfterDiscount(Math.round(shoppingCart.getGrandTotalBeforeDiscount() - discount));
                    shoppingCart.setSavedAmount(Math.round(shoppingCart.getGrandTotalBeforeDiscount() - shoppingCart.getGrandTotalAfterDiscount()));
                } else {
                    // If Coupon Not Present In Cart Then Set grandTotalAfterDiscount , savedAmount As 0
                    shoppingCart.setGrandTotalAfterDiscount(0);
                    shoppingCart.setSavedAmount(0);
                }
//                shoppingCart.getCartItems().stream().sorted(Comparator.comparing(CartItems::getCartItemsId)).close();
                return shoppingCartDao.saveShoppingCart(shoppingCart);
            } else {
                throw new CartItemNotFound("Cart Item Not Found");
            }
        } else {
            throw new RuntimeException("Product Not Found");
        }
    }

    @Override
    public ShoppingCart decreaseProductQuantityFromCart(String shoppingCartId, String cartItemsId) {
        ShoppingCart shoppingCart = shoppingCartDao.getCartById(shoppingCartId);
        CartItems cartItems = cartItemsDao.getCartItems(cartItemsId);
        // Check If Any Product Exists In Cart
        if (cartItems == null) {
            return shoppingCart;
        } else {
            Product product = restTemplate.getForObject("http://ProductService/product/get/" + cartItems.getProductId(), Product.class);
            if (product != null) {
                //If Cart Having Product Having Quantity 1 Then Delete Cart
                if (cartItems.getTotalPrice() == product.getProductPrice()) {
                    shoppingCart.getCartItems().remove(cartItems);
                    log.info("**********************" + shoppingCart.getCartItems());
                    cartItemsDao.deleteCart(cartItems.getCartItemsId());
                }
                // If Cart Having Product Having Quantity More Than 1 Then Decrease Quantity By 1
                else {
                    cartItems.setProductSelectedQuantity(cartItems.getProductSelectedQuantity() - 1);
                    cartItems.setTotalPrice(cartItems.getTotalPrice() - product.getProductPrice());
                    cartItemsDao.saveCartItem(cartItems);
                }
                shoppingCart.setGrandTotalBeforeDiscount(shoppingCart.getCartItems().stream().map(CartItems::getTotalPrice).reduce(0L, Long::sum));

                if (shoppingCart.getCoupon() != null && !shoppingCart.getCartItems().isEmpty()) {
                    double discount = (shoppingCart.getGrandTotalBeforeDiscount() * shoppingCart.getCoupon().getDiscount()) / 100;
                    shoppingCart.setGrandTotalAfterDiscount(Math.round(shoppingCart.getGrandTotalBeforeDiscount() - discount));
                    shoppingCart.setSavedAmount(Math.round(shoppingCart.getGrandTotalBeforeDiscount() - shoppingCart.getGrandTotalAfterDiscount()));
                } else {
                    shoppingCart.setCoupon(null);
                    shoppingCart.setGrandTotalAfterDiscount(0);
                    shoppingCart.setSavedAmount(0);
                }
//                shoppingCart.getCartItems().stream().sorted(Comparator.comparing(CartItems::getCartItemsId)).close();
                return shoppingCartDao.saveShoppingCart(shoppingCart);
            }
            return shoppingCart;
        }
    }

    @Override
    public ShoppingCart removeCartItemFromShoppingCart(String shoppingCartId, String cartItemsId) {
        cartItemsDao.deleteCart(cartItemsId);
        ShoppingCart cart = shoppingCartDao.getCartById(shoppingCartId);
        cart.setGrandTotalBeforeDiscount(cart.getCartItems().stream().map(CartItems::getTotalPrice).reduce(0L, Long::sum));

        if (cart.getCoupon() != null && !cart.getCartItems().isEmpty()) {
            double discount = (cart.getGrandTotalBeforeDiscount() * cart.getCoupon().getDiscount()) / 100;
            cart.setGrandTotalAfterDiscount(Math.round(cart.getGrandTotalBeforeDiscount() - discount));
            cart.setSavedAmount(Math.round(cart.getGrandTotalBeforeDiscount() - cart.getGrandTotalAfterDiscount()));
        } else {
            cart.setCoupon(null);
            cart.setGrandTotalAfterDiscount(0);
            cart.setSavedAmount(0);
        }
//        cart.getCartItems().stream().sorted(Comparator.comparing(CartItems::getCartItemsId)).close();
        return shoppingCartDao.saveShoppingCart(cart);
    }

    @Override
    public ShoppingCart getUser(String userId) {
        return shoppingCartDao.getByUserId(userId);
    }

    @Override
    public ShoppingCart cleanCart(String shoppingCartId, List<String> cartIds) {
        cartIds.forEach(id -> {
            try {
                cartItemsDao.deleteCart(id);
            } catch (CartItemNotFound e) {
                throw new RuntimeException(e);
            }
        });
        ShoppingCart shoppingCart = shoppingCartDao.getCartById(shoppingCartId);
        shoppingCart.setGrandTotalBeforeDiscount(shoppingCart.getCartItems().stream().map(CartItems::getTotalPrice).reduce(0L, Long::sum));
        shoppingCart.setCoupon(null);
        if (shoppingCart.getCoupon() == null) {
            shoppingCart.setGrandTotalAfterDiscount(0);
            shoppingCart.setSavedAmount(0);
        }
//        shoppingCart.getCartItems().stream().sorted(Comparator.comparing(CartItems::getCartItemsId)).close();
        return shoppingCartDao.saveShoppingCart(shoppingCart);
    }

    @Override
    public boolean checkUserHaveShoppingCart(String userId) {
        return shoppingCartDao.checkUserHaveShoppingCart(userId);
    }

    @Override
    public ShoppingCart addCouponToCart(String shoppingCartId, String couponName) {
        Coupon coupon = couponService.getByName(couponName);
        ShoppingCart shoppingCart = shoppingCartDao.getCartById(shoppingCartId);
        shoppingCart.setCoupon(coupon);

        double discount = (shoppingCart.getGrandTotalBeforeDiscount() * coupon.getDiscount()) / 100;      // Calculate Discount
        shoppingCart.setGrandTotalAfterDiscount(Math.round(shoppingCart.getGrandTotalBeforeDiscount() - discount));    // After Discount Price
        shoppingCart.setSavedAmount(Math.round(shoppingCart.getGrandTotalBeforeDiscount() - shoppingCart.getGrandTotalAfterDiscount()));   // Amount Saved

//        shoppingCart.getCartItems().stream().sorted(Comparator.comparing(CartItems::getCartItemsId)).close();
        return shoppingCartDao.saveShoppingCart(shoppingCart);
    }

    @Override
    public ShoppingCart deleteCouponFromCart(String shoppingCartId) {
        ShoppingCart shoppingCart = shoppingCartDao.getCartById(shoppingCartId);
        shoppingCart.setCoupon(null);
        if (shoppingCart.getCoupon() == null) {
            shoppingCart.setGrandTotalAfterDiscount(0);
            shoppingCart.setSavedAmount(0);
        }

//        shoppingCart.getCartItems().stream().sorted(Comparator.comparing(CartItems::getCartItemsId)).close();
        return shoppingCartDao.saveShoppingCart(shoppingCart);
    }

    @Override
    public ShoppingCart getShoppingCart(String shoppingCartId) {
        return shoppingCartDao.getCartById(shoppingCartId);
    }
}