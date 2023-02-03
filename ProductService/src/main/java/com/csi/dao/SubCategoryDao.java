package com.csi.dao;

import com.csi.model.SubCategory;

import java.util.List;

public interface SubCategoryDao {

    SubCategory saveSubCategory(SubCategory subCategory);

    SubCategory getSubCategoryByName(String subCategoryName);

    SubCategory getSubCategoryById(String subCategoryId);

    List<SubCategory> getAllSubCategory();

    void deleteSubCategory(String subCategoryId);
}
