package com.demo.demo.service.category;

import com.demo.demo.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
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
}
