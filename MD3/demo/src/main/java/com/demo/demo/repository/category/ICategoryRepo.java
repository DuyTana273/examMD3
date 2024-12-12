package com.demo.demo.repository.category;

import com.demo.demo.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ICategoryRepo {
    // Create
    void addCategories(Category Category);

    // Find
    List<Category> findAllCategory();
    Optional<Category> findCategoryById(int categoryID);
    Optional<Category> findCategoryByName(String categoryName);
    List<Category> searchCategory(String searchKeyword);

    // Update
    void updateCategory(Category Category);

    // Delete
    boolean deleteCategoryByName(String categoryName);

    // Phương thức ánh xạ từ ResultSet sang đối tượng Category
    Category mapCategory(ResultSet rs) throws SQLException;
}
