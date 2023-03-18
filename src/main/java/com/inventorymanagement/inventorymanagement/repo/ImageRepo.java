package com.inventorymanagement.inventorymanagement.repo;

import com.inventorymanagement.inventorymanagement.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<ProductImage,Long>{
}
