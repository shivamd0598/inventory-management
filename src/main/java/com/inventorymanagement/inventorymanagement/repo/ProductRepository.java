package com.inventorymanagement.inventorymanagement.repo;

import com.inventorymanagement.inventorymanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
