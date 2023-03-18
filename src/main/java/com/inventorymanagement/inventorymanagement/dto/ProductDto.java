package com.inventorymanagement.inventorymanagement.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ProductDto {
    private long id;

    private String name;

    private String description;

    private long quantity;

    private double price;
}
