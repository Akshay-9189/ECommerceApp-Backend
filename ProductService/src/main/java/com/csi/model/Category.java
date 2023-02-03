package com.csi.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(generator = "randomUUID1")
    @GenericGenerator(name = "randomUUID1", strategy = "com.csi.util.UUIDGenerator")
    private String categoryId;

    private String categoryName;
}
