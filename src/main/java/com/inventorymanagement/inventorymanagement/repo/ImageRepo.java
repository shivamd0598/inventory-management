package com.inventorymanagement.inventorymanagement.repo;

import com.inventorymanagement.inventorymanagement.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepo extends JpaRepository<ProductImage,Long>{
    @Query(value = "SELECT * FROM product_images p WHERE LOWER(p.name) = LOWER(:name) AND p.product_id = :id",nativeQuery = true)
    ProductImage findByProduct(@Param("name") String name,@Param("id") long id);
}
