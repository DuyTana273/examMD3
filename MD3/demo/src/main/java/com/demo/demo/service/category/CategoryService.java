package com.demo.demo.service.category;

import com.demo.demo.model.Category;
import com.demo.demo.repository.category.CategoryRepo;
import com.demo.demo.repository.category.ICategoryRepo;

import java.util.List;
import java.util.Optional;


public class CategoryService implements ICategoryService {
    private final ICategoryRepo iCategoryRepo = new CategoryRepo();

    // CREAT
    @Override
    public void addCategories(Category Category) {
        iCategoryRepo.addCategories(Category);
    }

    // FIND
    @Override
    public List<Category> findAllCategory() {
        return iCategoryRepo.findAllCategory();
    }

    @Override
    public Optional<Category> findCategoryById(int categoryID) {
        return iCategoryRepo.findCategoryById(categoryID);
    }

    @Override
    public Optional<Category> findCategoryByName(String categoryName) {
        return iCategoryRepo.findCategoryByName(categoryName);
    }

    @Override
    public List<Category> searchCategory(String searchKeyword) {
        return iCategoryRepo.searchCategory(searchKeyword);
    }

    // UPDATE
    public void updateCategory(Category Category) {
        iCategoryRepo.updateCategory(Category);
    }

    // DELETE
    public boolean deleteCategoryByName(String categoryName) {
        return iCategoryRepo.deleteCategoryByName(categoryName);
    }
}
