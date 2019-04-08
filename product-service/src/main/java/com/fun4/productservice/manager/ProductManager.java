package com.fun4.productservice.manager;

import com.fun4.productservice.model.Product;
import com.fun4.productservice.model.SortingOrder;
import com.fun4.productservice.model.SortingType;
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

    public List<Product> getMultipleProductsById(String[] ids) {
        return this.productRepository.getMultipleProductsById(ids);
    }

    public Product addProduct(Product product) throws Exception{
        return this.productRepository.addProduct(product);
    }

    public Product updateProduct(Product product){
        return this.productRepository.updateProduct(product);
    }

    public void deleteProduct(int productId) { this.productRepository.deleteProduct(productId); }

    public List<Product> getProducts(Integer startIndex, Integer pageSize, String type, String order, String shopId, String categoryId) {
        return this.productRepository.getAllProducts(startIndex, pageSize, type, order, shopId, categoryId);
    }

    public List<Product> getProductsForUser(int userId, Integer startIndex, Integer pageSize){
        return this.productRepository.getProductsForUser(userId, startIndex, pageSize);
    }

    public int getTotalCount(){
        return this.productRepository.getTotalCount();
    }

    public int getTotalCountForShop(int shopId) {
        return this.productRepository.getTotalCountForShop(shopId);
    }
}
