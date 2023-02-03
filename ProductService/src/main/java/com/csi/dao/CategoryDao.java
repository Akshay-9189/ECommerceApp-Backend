package com.csi.dao;

import com.csi.model.Category;

import java.util.List;

public interface CategoryDao {

    Category saveCategory(Category category);

    Category getCategoryByName(String categoryName);

    Category getCategoryById(String categoryId);

    List<Category> getAllCategory();

    void deleteCategory(String categoryId);
}
