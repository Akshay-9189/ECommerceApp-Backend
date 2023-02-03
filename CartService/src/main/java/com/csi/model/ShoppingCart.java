package com.csi.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ShoppingCart {

    @Id
    @GeneratedValue(generator = "randomUUID1")
    @GenericGenerator(name = "randomUUID1", strategy = "com.csi.util.UUIDGenerator")
    private String shoppingCartId;

    @Column(unique = true, updatable = false)
    private String userId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "shoppingCartId", referencedColumnName = "shoppingCartId")
    private Set<CartItems> cartItems = new HashSet<>();

    private long grandTotalBeforeDiscount;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Coupon.class)
    @JoinColumn(name = "couponId")
    private Coupon coupon;

    private long grandTotalAfterDiscount;

    private long savedAmount;
}
