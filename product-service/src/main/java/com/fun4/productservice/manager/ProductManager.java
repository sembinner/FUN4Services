package com.fun4.productservice.manager;

import com.fun4.productservice.model.Product;
import com.fun4.productservice.repository.ProductRepository;

import java.util.List;

public class ProductManager {
    private ProductRepository productRepository;

    public ProductManager() {
        this.productRepository = new ProductRepository();
    }

    public Product getProductById(int productId){
        return this.productRepository.getProductById(productId);
    }

    public Product addProduct(Product product) throws Exception{
        return this.productRepository.addProduct(product);
    }

    public List<Product> getAllProducts() {
        return this.productRepository.getAllProducts();
    }
}
