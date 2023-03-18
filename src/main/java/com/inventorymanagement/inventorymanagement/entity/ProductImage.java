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

    @Column(unique = true)
    private String name;
    private String type;
    @Column(name = "imagedata",columnDefinition = "LONGBLOB")
    private byte[] imageData;
    @ManyToOne
    private Product product;
}
