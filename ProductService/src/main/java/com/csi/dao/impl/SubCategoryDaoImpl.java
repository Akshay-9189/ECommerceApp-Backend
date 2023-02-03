package com.csi.dao.impl;

import com.csi.dao.SubCategoryDao;
import com.csi.exception.SubCategoryNotFound;
import com.csi.model.SubCategory;
import com.csi.repo.SubCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class SubCategoryDaoImpl implements SubCategoryDao {

    @Autowired
    private SubCategoryRepo subCategoryRepo;

    @Override
    public SubCategory saveSubCategory(SubCategory subCategory) {
        return subCategoryRepo.save(subCategory);
    }

    @Override
    public SubCategory getSubCategoryByName(String subCategoryName) {
        return subCategoryRepo.findBySubCategoryName(subCategoryName);
    }

    @Override
    public SubCategory getSubCategoryById(String subCategoryId) {
        return subCategoryRepo.findById(subCategoryId).orElseThrow(() -> new SubCategoryNotFound("Sub Category Not Found !!"));
    }

    @Override
    public List<SubCategory> getAllSubCategory() {
        return subCategoryRepo.findAll();
    }

    @Override
    public void deleteSubCategory(String subCategoryId) {
        SubCategory subCategory = getSubCategoryById(subCategoryId);
        subCategoryRepo.delete(subCategory);
    }
}
