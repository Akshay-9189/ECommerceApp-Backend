package com.fullstack.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class WishlistProduct {
    @Id
    @GeneratedValue(generator = "customUUID")
    @GenericGenerator(name = "customUUID", strategy = "com.fullstack.util.UUIDGenerator")
    private String id;

    private String productId;
}
