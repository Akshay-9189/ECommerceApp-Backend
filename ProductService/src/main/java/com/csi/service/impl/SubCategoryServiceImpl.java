package com.csi.service.impl;

import com.csi.dao.CategoryDao;
import com.csi.dao.SubCategoryDao;
import com.csi.dto.subcategory.ListSubCategoryDTO;
import com.csi.dto.subcategory.SaveSubCategoryDTO;
import com.csi.external.OrderService;
import com.csi.model.Category;
import com.csi.model.SubCategory;
import com.csi.service.ProductService;
import com.csi.service.SubCategoryService;
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
