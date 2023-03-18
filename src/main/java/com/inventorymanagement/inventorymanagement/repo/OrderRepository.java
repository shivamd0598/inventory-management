package com.inventorymanagement.inventorymanagement.repo;

import com.inventorymanagement.inventorymanagement.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
