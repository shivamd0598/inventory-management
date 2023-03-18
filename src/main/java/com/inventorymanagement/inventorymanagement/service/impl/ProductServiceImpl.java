package com.inventorymanagement.inventorymanagement.service.impl;

import com.inventorymanagement.inventorymanagement.dto.ProductDto;
import com.inventorymanagement.inventorymanagement.entity.Product;
import com.inventorymanagement.inventorymanagement.entity.ProductImage;
import com.inventorymanagement.inventorymanagement.exception.CustomException;
import com.inventorymanagement.inventorymanagement.repo.ImageRepo;
import com.inventorymanagement.inventorymanagement.repo.ProductRepository;
import com.inventorymanagement.inventorymanagement.service.ProductService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Builder
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ImageRepo imageRepo;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public long createProduct(ProductDto productDto, MultipartFile file) throws IOException {
      log.info("Saving Product....");
        Product product=Product.builder()
                        .name(productDto.getName())
                        .description(productDto.getDescription())
                        .quantity(productDto.getQuantity())
                        .price(productDto.getPrice())
                        .build();
        product=productRepository.save(product);

        ProductImage productImage=new ProductImage();
        productImage.setName(file.getOriginalFilename());
        productImage.setType(file.getContentType());
        productImage.setImageData(file.getBytes());
        productImage.setProduct(product);
        imageRepo.save(productImage);

        log.info("Product Created");
        return product.getId();
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products=productRepository.findAll();
        return products.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(long id) {
        Product product=productRepository.findById(id).orElseThrow(()->new CustomException(String.format("Product with %s id not found",id),"PRODUCT_NOT_FOUND",404));
        return mapToDTO(product);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto,long id) {
        Product product=productRepository.findById(id).orElseThrow(()->new CustomException(String.format("Product with %s id not found",id),"PRODUCT_NOT_FOUND",404));

        log.info("Updating Product....");
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setQuantity(productDto.getQuantity());
        product.setPrice(productDto.getPrice());
        log.info("Product Created");

        Product updatedProduct=productRepository.save(product);
        return mapToDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(long id) {
        Product product=productRepository.findById(id).orElseThrow(()->new CustomException(String.format("Product with %s id not found",id),"PRODUCT_NOT_FOUND",404));
        productRepository.delete(product);
    }

    @Override
    public byte[] downloadImage(long id, String fileName) {
        Product product=productRepository.findById(id).orElseThrow(()->new CustomException(String.format("Product with %s id not found",id),"PRODUCT_NOT_FOUND",404));
        Optional<ProductImage> productImage=imageRepo.findById(product.getId());
        return productImage.get().getImageData();
    }

    public boolean reduceQuantity(long id,long quantity){
        Product product=productRepository.findById(id).orElseThrow(()->new CustomException(String.format("Product with %s id not found",id),"PRODUCT_NOT_FOUND",404));
        if(product.getQuantity()<quantity){
            throw new CustomException(String.format("Product with %s id does not have sufficient",id),"INSUFFICIENT_QUANTITY",500);
        }
        product.setQuantity(product.getQuantity()-quantity);
        return true;
    }

    //Convert Entity to DTO
    public ProductDto mapToDTO(Product product){
        ProductDto productDto=new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setQuantity(product.getQuantity());
        productDto.setPrice(product.getPrice());

        return productDto;
    }

    //Convert Entity to DTO
    public Product mapToEntity(ProductDto productDto){
        Product product= new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setQuantity(productDto.getQuantity());
        product.setPrice(productDto.getPrice());

        return product;
    }

}
