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
public class Category {
    @Id
    @GeneratedValue(generator = "randomUUID1")
    @GenericGenerator(name = "randomUUID1", strategy = "com.fullstack.util.UUIDGenerator")
    private String categoryId;

    private String categoryName;
}
