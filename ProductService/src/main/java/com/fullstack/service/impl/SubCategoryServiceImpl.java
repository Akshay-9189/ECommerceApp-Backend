package com.fullstack.service.impl;

import com.fullstack.dao.CategoryDao;
import com.fullstack.dao.SubCategoryDao;
import com.fullstack.dto.subcategory.ListSubCategoryDTO;
import com.fullstack.dto.subcategory.SaveSubCategoryDTO;
import com.fullstack.external.OrderService;
import com.fullstack.model.Category;
import com.fullstack.model.SubCategory;
import com.fullstack.service.ProductService;
import com.fullstack.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private SubCategoryDao subCategoryDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Override
    public SubCategory saveSubCategory(SaveSubCategoryDTO saveSubCategoryDTO) {
        SubCategory subCategory = new SubCategory();
        subCategory.setSubCategoryName(saveSubCategoryDTO.getSubCategoryName());

        Category category = categoryDao.getCategoryByName(saveSubCategoryDTO.getCategoryName());
        subCategory.setCategory(category);
        return subCategoryDao.saveSubCategory(subCategory);
    }

    @Override
    public SubCategory updateSubCategory(SaveSubCategoryDTO saveSubCategoryDTO, String subCategoryId) {
        SubCategory subCategory = subCategoryDao.getSubCategoryById(subCategoryId);
        subCategory.setSubCategoryName(saveSubCategoryDTO.getSubCategoryName());

        Category category = categoryDao.getCategoryByName(saveSubCategoryDTO.getCategoryName());
        subCategory.setCategory(category);
        return subCategoryDao.saveSubCategory(subCategory);
    }

    @Override
    public SubCategory getSubCategoryByName(String subCategoryName) {
        return subCategoryDao.getSubCategoryByName(subCategoryName);
    }

    @Override
    public List<SubCategory> getAllSubCategory() {
        return subCategoryDao.getAllSubCategory();
    }

    @Override
    public void deleteSubCategory(String subCategoryId) {
        SubCategory subCategory = subCategoryDao.getSubCategoryById(subCategoryId);
        subCategory.setCategory(null);

        productService.getAllProduct().stream().filter(prod -> prod.getSubCategory().getSubCategoryId().equals(subCategory.getSubCategoryId()))
                .forEach(pr -> {
                    productService.deleteProduct(pr.getProductId());
                    orderService.deleteOrderByProductId(pr.getProductId());
                });

        subCategoryDao.deleteSubCategory(subCategoryId);
    }

    @Override
    public List<ListSubCategoryDTO> getAllSubCategories() {
        List<ListSubCategoryDTO> listSubCategoryDTOS = new ArrayList<>();
        subCategoryDao.getAllSubCategory().forEach(subCat -> {
            ListSubCategoryDTO listSubCategoryDTO = new ListSubCategoryDTO(subCat.getSubCategoryId(), subCat.getSubCategoryName());
            listSubCategoryDTOS.add(listSubCategoryDTO);
        });
        return listSubCategoryDTOS;
    }
}
