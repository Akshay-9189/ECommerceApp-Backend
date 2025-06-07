package com.fullstack.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(generator = "randomUUID3")
    @GenericGenerator(name = "randomUUID3", strategy = "com.fullstack.util.UUIDGenerator")
    private String productId;

    private String productName;

    private double productWeight;

    private String productDescription;

    private String productCode;

    private int productPrice;

    private int productTotalQuantityAvailable;

    private String imageURL;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, targetEntity = SubCategory.class)
    @JoinColumn(name = "subCategoryId")
    private SubCategory subCategory;
}
