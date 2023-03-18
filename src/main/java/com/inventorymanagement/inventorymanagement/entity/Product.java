package com.inventorymanagement.inventorymanagement.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.data.jpa.repository.query.Procedure;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "products")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "quantity",nullable = false)
    private long quantity;

    @Column(name = "price",nullable = false)
    private double price;

    @OneToMany(mappedBy = "product")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<ProductImage> productImage;
}
