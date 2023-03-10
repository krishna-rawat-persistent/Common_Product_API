package com.psl.common_productapi.service;

import java.util.List;

import com.psl.common_productapi.model.Product;

public interface ProductService {

    Product saveProduct(Product product);
    Product getProduct(Long productId);
    List<Product> getAllProducts();
    Product updateProduct(Product product,Long productId);
    void deleteProduct(Long productId);
    void deleteAllProducts();
}
