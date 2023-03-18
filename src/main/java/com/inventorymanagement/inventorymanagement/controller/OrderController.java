package com.inventorymanagement.inventorymanagement.controller;

import com.inventorymanagement.inventorymanagement.dto.OrderDto;
import com.inventorymanagement.inventorymanagement.dto.ProductDto;
import com.inventorymanagement.inventorymanagement.service.OrderService;
import com.inventorymanagement.inventorymanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<Long> placeOrder(@RequestBody OrderDto orderDto){
        return new ResponseEntity<>(orderService.placeOrder(orderDto), HttpStatus.CREATED);
    }
}
