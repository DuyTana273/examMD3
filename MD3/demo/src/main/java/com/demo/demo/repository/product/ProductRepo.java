package com.demo.demo.repository.product;

import com.demo.demo.model.Product;
import com.demo.demo.repository.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepo implements IProductRepo {

    // Các câu truy vấn SQL
    private static final String INSERT_PRODUCTS = "INSERT INTO Products (productName, productDescription, productPrice, productStock, productColor, categoryID) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_ALL_PRODUCTS = "SELECT p.productID, p.productName, p.productDescription, p.productPrice, p.productStock, p.productColor, c.categoryID, c.categoryName " +
            "FROM Products p " +
            "JOIN Category c ON p.categoryID = c.categoryID";

    private static final String SELECT_PRODUCT_BY_ID = "SELECT p.productID, p.productName, p.productDescription, p.productPrice, p.productStock, p.productColor, c.categoryID, c.categoryName " +
            "FROM Products p " +
            "JOIN Category c ON p.categoryID = c.categoryID " +
            "WHERE p.productID = ?";

    private static final String SELECT_PRODUCT_BY_NAME = "SELECT p.productID, p.productName, p.productDescription, p.productPrice, p.productStock, p.productColor, c.categoryID, c.categoryName " +
            "FROM Products p " +
            "JOIN Category c ON p.categoryID = c.categoryID " +
            "WHERE p.productName = ?";

    private static final String SEARCH_PRODUCT = "SELECT p.productID, p.productName, p.productDescription, p.productPrice, p.productStock, p.productColor, c.categoryID, c.categoryName " +
            "FROM Products p " +
            "JOIN Category c ON p.categoryID = c.categoryID " +
            "WHERE p.productName LIKE ? OR CAST(p.productPrice AS CHAR) LIKE ? OR CAST(p.productStock AS CHAR) LIKE ? OR c.categoryName LIKE ?";

    private static final String UPDATE_PRODUCTS = "UPDATE Products SET productName = ?, productDescription = ?, productPrice = ?, productStock = ?, productColor = ?, categoryID = ? WHERE productID = ?";

    private static final String DELETE_PRODUCTS_BY_ID = "DELETE FROM Products WHERE productID = ?";
    private static final String DELETE_PRODUCTS_BY_NAME = "DELETE FROM Products WHERE productName = ?";



    @Override
    public void addProduct(Product product) {
        try (Connection connection = BaseRepository.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTS)) {
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductDescription());
            preparedStatement.setDouble(3, product.getProductPrice());
            preparedStatement.setInt(4, product.getProductStock());
            preparedStatement.setString(5, product.getProductColor());
            preparedStatement.setInt(6, product.getCategoryID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find
    @Override
    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();

        try (Connection connection = BaseRepository.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                products.add(mapProduct(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
    @Override
    public Optional<Product> findProductById(int productID) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {

            preparedStatement.setInt(1, productID);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(mapProduct(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Product> findProductByName(String productName) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_NAME)) {

            preparedStatement.setString(1, productName);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(mapProduct(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Product> searchProduct(String searchKeyword) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_PRODUCT)) {
            String searchPattern = "%" + searchKeyword + "%";
            preparedStatement.setString(1, searchPattern);
            preparedStatement.setString(2, searchPattern);
            preparedStatement.setString(3, searchPattern);
            preparedStatement.setString(4, searchPattern);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                products.add(mapProduct(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    // Update
    @Override
    public void updateProduct(Product product) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCTS)) {
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductDescription());
            preparedStatement.setDouble(3, product.getProductPrice());
            preparedStatement.setInt(4, product.getProductStock());
            preparedStatement.setString(5, product.getProductColor());
            preparedStatement.setInt(6, product.getCategoryID());
            preparedStatement.setInt(7, product.getProductID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /// Delete
    public boolean deleteProductById(int productID) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCTS_BY_ID)) {

            preparedStatement.setInt(1, productID);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteProductByName(String productName) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCTS_BY_NAME)) {

            preparedStatement.setString(1, productName);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Map
    public Product mapProduct(ResultSet rs) throws SQLException {
        return new Product(
                rs.getInt("productID"),
                rs.getString("productName"),
                rs.getDouble("productPrice"),
                rs.getInt("productStock"),
                rs.getString("productColor"),
                rs.getString("productDescription"),
                rs.getInt("categoryID"),
                rs.getString("categoryName")
        );
    }

}
