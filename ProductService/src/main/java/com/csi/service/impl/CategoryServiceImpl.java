package com.csi.service.impl;

import com.csi.dao.CategoryDao;
import com.csi.external.OrderService;
import com.csi.model.Category;
import com.csi.service.CategoryService;
import com.csi.service.ProductService;
import com.csi.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Override
    public Category saveCategory(Category category) {
        return categoryDao.saveCategory(category);
    }

    @Override
    public Category updateCategory(Category category, String categoryId) {
        Category category1 = categoryDao.getCategoryById(categoryId);
        category1.setCategoryName(category.getCategoryName());
        return categoryDao.saveCategory(category1);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryDao.getAllCategory();
    }

    @Override
    public void deleteCategory(String categoryId) {
        productService.getAllProduct().stream().filter(prod -> prod.getSubCategory().getCategory().getCategoryId()
                .equals(categoryId)).forEach(pr -> {
            subCategoryService.deleteSubCategory(pr.getSubCategory().getSubCategoryId());

            orderService.deleteOrderByProductId(pr.getProductId());
        });

        categoryDao.deleteCategory(categoryId);
    }
}