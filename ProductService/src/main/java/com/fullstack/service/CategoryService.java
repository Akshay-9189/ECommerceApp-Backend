package com.fullstack.service;

import com.fullstack.model.Category;

import java.util.List;

public interface CategoryService {

    Category saveCategory(Category category);

    Category updateCategory(Category category, String categoryId);

    List<Category> getAllCategory();

    void deleteCategory(String categoryId);
}
