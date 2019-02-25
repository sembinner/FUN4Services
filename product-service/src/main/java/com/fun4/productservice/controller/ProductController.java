package com.fun4.productservice.controller;

import com.fun4.productservice.manager.ProductManager;
import com.fun4.productservice.model.Product;
import com.fun4.productservice.viewmodel.CreateProductViewmodel;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Api(value="/products",description="Product Service",produces ="application/json")
@RequestMapping("/products")
public class ProductController {
    ProductManager productManager;

    public ProductController() {
        this.productManager = new ProductManager();
    }

    // Get all Products
    @GetMapping()
    public ResponseEntity getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(this.productManager.getAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity getProductById(@PathVariable(value = "productId") int productId){
        return ResponseEntity.status(HttpStatus.OK).body(productManager.getProductById(productId));
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(CreateProductViewmodel viewmodel){
        Product product  = new Product(viewmodel.getName(), viewmodel.getDescription(), viewmodel.getPrice(), viewmodel.getUserId());
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.productManager.addProduct(product));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}
