package com.demo.demo.service.product;

import com.demo.demo.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    // Create
    void addProduct(Product product);

    // Find
    List<Product> findAllProducts();
    Optional<Product> findProductById(int productID);
    Optional<Product> findProductByName(String productName);
    List<Product> searchProduct(String searchKeyword);

    // Update
    void updateProduct(Product product);

    // Delete
    boolean deleteProductById(int productID);
    boolean deleteProductByName(String productName);

}
