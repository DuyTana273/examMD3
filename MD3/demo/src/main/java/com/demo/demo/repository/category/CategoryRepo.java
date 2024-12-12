package com.demo.demo.repository.category;

import com.demo.demo.model.Category;
import com.demo.demo.repository.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRepo implements ICategoryRepo{

    // Các câu truy vấn SQL
    private static final String INSERT_CATEGORY = "INSERT INTO Category (categoryName) VALUES (?)";
    private static final String SELECT_ALL_CATEGORY = "SELECT * FROM Category";
    private static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM Category WHERE categoryID = ?";
    private static final String SELECT_CATEGORY_BY_NAME = "SELECT * FROM Category WHERE categoryName = ?";
    private static final String SEARCH_CATEGORY = "SELECT * FROM Category WHERE 1=1 " +
            "AND categoryName LIKE ?";
    private static final String UPDATE_CATEGORY = "UPDATE Category SET categoryName = ? WHERE categoryID = ?";
    private static final String DELETE_CATEGORY_BY_NAME = "DELETE FROM Category WHERE categoryName = ?";

    // Create
    @Override
    public void addCategories(Category Category) {
        try(Connection connection = BaseRepository.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY)) {

            preparedStatement.setString(1, Category.getCategoryName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find
    @Override
    public List<Category> findAllCategory() {
        List<Category> categoryList = new ArrayList<>();

        try (Connection connection = BaseRepository.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                categoryList.add(mapCategory(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryList;
    }

    @Override
    public Optional<Category> findCategoryById(int categoryID) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID)) {

            preparedStatement.setInt(1, categoryID);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(mapCategory(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Category> findCategoryByName(String categoryName) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_NAME)) {

            preparedStatement.setString(1, categoryName);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(mapCategory(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Category> searchCategory(String searchKeyword) {
        List<Category> categoryList = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_CATEGORY)) {
            String searchPattern = "%" + searchKeyword + "%";
            preparedStatement.setString(1, searchPattern);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                categoryList.add(mapCategory(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryList;
    }

    // Update
    public void updateCategory(Category Category) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY)) {
            preparedStatement.setString(1, Category.getCategoryName());
            preparedStatement.setInt(2, Category.getCategoryID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /// Delete
    public boolean deleteCategoryByName(String categoryName) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY_BY_NAME)) {
            preparedStatement.setString(1, categoryName);

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Phương thức ánh xạ từ ResultSet sang đối tượng Categories
    public Category mapCategory(ResultSet rs) throws SQLException {
        return new Category(
                rs.getInt("categoryID"),
                rs.getString("categoryName")
        );
    }
}
