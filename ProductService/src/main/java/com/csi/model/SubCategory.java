package com.csi.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class SubCategory {

    @Id
    @GeneratedValue(generator = "randomUUID2")
    @GenericGenerator(name = "randomUUID2", strategy = "com.csi.util.UUIDGenerator")
    private String subCategoryId;

    private String subCategoryName;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, targetEntity = Category.class)
    @JoinColumn(name = "categoryId")
    private Category category;
}
