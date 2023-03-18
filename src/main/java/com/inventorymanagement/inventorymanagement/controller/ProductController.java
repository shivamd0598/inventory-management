package com.inventorymanagement.inventorymanagement.controller;

import com.inventorymanagement.inventorymanagement.dto.ProductDto;
import com.inventorymanagement.inventorymanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Long> createProduct(@RequestBody ProductDto productDto, @RequestParam MultipartFile file) throws IOException {
        return new ResponseEntity<>(productService.createProduct(productDto,file), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable(name = "id") long id){
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@RequestBody ProductDto productDto, @PathVariable(name = "id") long id){
        return productService.updateProduct(productDto,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product deleted successfully!!!",HttpStatus.OK);
    }
    @GetMapping("/download/{id}/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable(name = "id") long id, @PathVariable(name = "fileName") String fileName){
        byte[] image=productService.downloadImage(id,fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }
}
