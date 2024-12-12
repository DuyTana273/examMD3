package com.demo.demo.repository.product;

import com.demo.demo.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IProductRepo {
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

    // Ánh Xạ
    Product mapProduct(ResultSet rs) throws SQLException;
}