package com.csi.controller;

import com.csi.model.Category;
import com.csi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:8000", allowCredentials = "true")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<?> saveCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.saveCategory(category), HttpStatus.OK);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<?> updateCategory(@RequestBody Category category, @PathVariable String categoryId) {
        return new ResponseEntity<>(categoryService.updateCategory(category, categoryId), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
    }

    @DeleteMapping("/delete-category/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable String categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Category Deleted Successfully", HttpStatus.OK);
    }
}