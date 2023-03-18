package com.inventorymanagement.inventorymanagement.service.impl;

import com.inventorymanagement.inventorymanagement.dto.OrderDto;
import com.inventorymanagement.inventorymanagement.entity.Order;
import com.inventorymanagement.inventorymanagement.repo.OrderRepository;
import com.inventorymanagement.inventorymanagement.service.OrderService;
import com.inventorymanagement.inventorymanagement.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    private ProductServiceImpl productService;
    @Override
    public long placeOrder(OrderDto orderDto) {
        log.info("Placing Order....");
        Order order=Order.builder()
                .totalAmount(orderDto.getTotalAmount())
                .productId(orderDto.getProductId())
                .quantity(orderDto.getQuantity())
                .status("SUCCESS")
                .build();
        if(productService.reduceQuantity(order.getProductId(), order.getQuantity())){
            order=orderRepository.save(order);
            log.info("Order Placed Successfully!!!");
            return order.getId();
        }
        else{
            order.setStatus("FAILED");
            return 0;
        }
    }


}
