package com.csi.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist {

    @Id
    @GeneratedValue(generator = "randomUUID")
    @GenericGenerator(name = "randomUUID", strategy = "com.csi.util.UUIDGenerator")
    private String wishListId;

    @Column(updatable = false)
    private String userId;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "wishListId" , referencedColumnName = "wishListId")
    private Set<WishlistProduct> wishlistProductSet = new HashSet<>();
}
