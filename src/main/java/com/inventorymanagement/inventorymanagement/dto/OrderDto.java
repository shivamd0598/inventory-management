package com.inventorymanagement.inventorymanagement.dto;

import lombok.Data;

@Data
public class OrderDto {
    private long id;

    private long productId;

    private long quantity;

    private String status;

    private double totalAmount;
}
