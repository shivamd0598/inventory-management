package com.inventorymanagement.inventorymanagement.service;

import com.inventorymanagement.inventorymanagement.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    public long createProduct(ProductDto productDto);
    public List<ProductDto> getAllProducts();

    public ProductDto getProductById(long id);

    public ProductDto updateProduct(ProductDto productDto,long id);

    public void deleteProduct(long id);

    public void uploadImage(long id, MultipartFile file) throws IOException;

    public byte[] downloadImage(long id,String fileName);
}
