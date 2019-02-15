package com.example.productservice.controller;

import com.example.productservice.exception.ResourceNotFoundException;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    // Get all Products
    @GetMapping()
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Create new Product
    @PostMapping()
    public Product createProduct(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    // Get a single Product - By Id
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable(value = "id") Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
    }

    // Update a Product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable(value = "id") Long productId,
                                 @Valid @RequestBody Product productDetails) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());

        Product updatedProduct = productRepository.save(product);
        return updatedProduct;
    }

    // Delete a Product
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        productRepository.delete(product);

        return ResponseEntity.ok().build();
    }
}
