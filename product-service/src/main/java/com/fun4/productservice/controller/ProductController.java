package com.fun4.productservice.controller;

import com.fun4.productservice.manager.ProductManager;
import com.fun4.productservice.model.Product;
import com.fun4.productservice.model.SortingOrder;
import com.fun4.productservice.model.SortingType;
import com.fun4.productservice.viewmodel.CreateProductViewmodel;
import com.fun4.productservice.viewmodel.UpdateProductViewmodel;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
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

    // Get Products
    @GetMapping()
    public ResponseEntity getProducts(
            @RequestParam(value = "startIndex", required = false) Integer startIndex,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "order", required = false) String order
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(this.productManager.getProducts(startIndex, pageSize, type, order));
    }

    @GetMapping("/totalCount")
    public ResponseEntity getTotalCount(){
        return ResponseEntity.status(HttpStatus.OK).body(this.productManager.getTotalCount());
    }

    // Get single product - by id
    @GetMapping("/{productId}")
    public ResponseEntity getProductById(@PathVariable(value = "productId") int productId){
        return ResponseEntity.status(HttpStatus.OK).body(productManager.getProductById(productId));
    }

    // Get product per user
    @GetMapping("/users/{userId}")
    public ResponseEntity getProductForUser(
            @PathVariable(value = "userId") int userId,
            @RequestParam(value = "startIndex", required = false) Integer startIndex,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
            ){
        return ResponseEntity.status(HttpStatus.OK).body(productManager.getProductsForUser(userId, startIndex, pageSize));
    }

    // Create new product
    @PostMapping("/add")
    public ResponseEntity addProduct(CreateProductViewmodel viewmodel){
        Product product  = new Product(viewmodel.getName(), viewmodel.getDescription(), viewmodel.getPrice(), viewmodel.getUserId());
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.productManager.addProduct(product));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Update product
    @PutMapping("/{id}")
    public ResponseEntity updateProduct(UpdateProductViewmodel viewmodel){
        Product product = this.productManager.getProductById(viewmodel.getId());

        product.updateProduct(viewmodel.getName(), viewmodel.getDescription(), viewmodel.getPrice());

        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.productManager.updateProduct(product));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Delete a product - by id
    @DeleteMapping("/{productId}")
    public ResponseEntity deleteProduct(@PathVariable(value = "productId") int productId){
        try {
            this.productManager.deleteProduct(productId);
            return ResponseEntity.status(HttpStatus.OK).body("Product has been deleted");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
