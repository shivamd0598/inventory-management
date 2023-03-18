package com.inventorymanagement.inventorymanagement.service;

import com.inventorymanagement.inventorymanagement.dto.OrderDto;

public interface OrderService {
    public long placeOrder(OrderDto orderDto);
}
