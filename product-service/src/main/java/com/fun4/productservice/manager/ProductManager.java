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

    public Product updateProduct(Product product){
        return this.productRepository.updateProduct(product);
    }

    public void deleteProduct(int productId) { this.productRepository.deleteProduct(productId); }

    public List<Product> getProducts(Integer startIndex, Integer pageSize) {
        if (startIndex != null && pageSize != null){
            return this.productRepository.getProducts(startIndex, pageSize);
        }
        System.out.println(startIndex);
        System.out.println(pageSize);
        return this.productRepository.getAllProducts();
    }

    public List<Product> getProductsForUser(int userId){
        return this.productRepository.getProductsForUser(userId);
    }
}
