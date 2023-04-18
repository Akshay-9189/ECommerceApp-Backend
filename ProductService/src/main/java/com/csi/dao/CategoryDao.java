package com.csi.dao;

import com.csi.model.Category;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface CategoryDao {

    @CacheEvict(value = {"CategoryByName", "CategoryById", "Categories", "SubCategories", "SubCategoryById",
            "SubCategoryByName", "Products", "ProductById", "ProductByName"}, allEntries = true)
    Category saveCategory(Category category);

    @Cacheable(value = "CategoryByName")
    Category getCategoryByName(String categoryName);

    @Cacheable(value = "CategoryById")
    Category getCategoryById(String categoryId);

    @Cacheable(value = "Categories")
    List<Category> getAllCategory();

    @CacheEvict(value = {"CategoryByName", "CategoryById", "Categories", "SubCategories", "SubCategoryById",
            "SubCategoryByName", "Products", "ProductById", "ProductByName"}, allEntries = true)
    void deleteCategory(String categoryId);
}
