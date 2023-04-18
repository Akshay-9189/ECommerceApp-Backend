package com.csi.dao;

import com.csi.model.SubCategory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface SubCategoryDao {

    @CacheEvict(value = {"SubCategories", "SubCategoryById", "SubCategoryByName", "Products", "ProductById", "ProductByName"},
            allEntries = true)
    SubCategory saveSubCategory(SubCategory subCategory);

    @Cacheable(value = "SubCategoryByName")
    SubCategory getSubCategoryByName(String subCategoryName);

    @Cacheable(value = "SubCategoryById")
    SubCategory getSubCategoryById(String subCategoryId);

    @Cacheable(value = "SubCategories")
    List<SubCategory> getAllSubCategory();

    @CacheEvict(value = {"SubCategories", "SubCategoryById", "SubCategoryByName", "Products", "ProductById", "ProductByName"},
            allEntries = true)
    void deleteSubCategory(String subCategoryId);
}