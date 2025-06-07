package com.fullstack.dao.impl;

import com.fullstack.dao.CategoryDao;
import com.fullstack.exception.CategoryNotFound;
import com.fullstack.model.Category;
import com.fullstack.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return categoryRepo.findByCategoryName(categoryName);
    }

    @Override
    public Category getCategoryById(String categoryId) {
        return categoryRepo.findById(categoryId).orElseThrow(() -> new CategoryNotFound("Category Not Found !!!!!"));
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public void deleteCategory(String categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new CategoryNotFound("Category Not Found !!!!!!"));
        categoryRepo.delete(category);
    }
}
