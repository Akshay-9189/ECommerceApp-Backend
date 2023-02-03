package com.csi.service;

import com.csi.dto.subcategory.ListSubCategoryDTO;
import com.csi.dto.subcategory.SaveSubCategoryDTO;
import com.csi.model.SubCategory;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface SubCategoryService {

    SubCategory saveSubCategory(SaveSubCategoryDTO saveSubCategoryDTO);

    SubCategory updateSubCategory(SaveSubCategoryDTO saveSubCategoryDTO, String subCategoryId);

    @Cacheable(value = "subCategoryName")
    SubCategory getSubCategoryByName(String subCategoryName);

    List<SubCategory> getAllSubCategory();

    void deleteSubCategory(String subCategoryId);

    List<ListSubCategoryDTO> getAllSubCategories();
}
