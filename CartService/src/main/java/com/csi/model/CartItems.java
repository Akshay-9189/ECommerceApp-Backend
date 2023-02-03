package com.csi.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CartItems {

    @Id
    @GeneratedValue(generator = "randomUUID2")
    @GenericGenerator(name = "randomUUID2" , strategy = "com.csi.util.UUIDGenerator")
    private String cartItemsId ;

    private String productId;

    private int productSelectedQuantity;

    private long totalPrice;
}
