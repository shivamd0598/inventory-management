package com.inventorymanagement.inventorymanagement.entity;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "product_images")
@Data
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String type;
    @Column(name = "imagedata")
    private byte[] imageData;
    @ManyToOne
    private Product product;
}
