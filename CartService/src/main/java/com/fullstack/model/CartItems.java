package com.fullstack.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CartItems {

    @Id
    @GeneratedValue(generator = "randomUUID2")
    @GenericGenerator(name = "randomUUID2", strategy = "com.fullstack.util.UUIDGenerator")
    private String cartItemsId ;

    private String productId;

    private int productSelectedQuantity;

    private long totalPrice;
}
