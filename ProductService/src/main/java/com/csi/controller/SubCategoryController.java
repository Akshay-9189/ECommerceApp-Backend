package com.csi.controller;

import com.csi.dto.subcategory.SaveSubCategoryDTO;
import com.csi.repo.SubCategoryRepo;
import com.csi.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sub-category")
@CrossOrigin(origins = "http://localhost:8000", allowCredentials = "true")

public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private SubCategoryRepo subCategoryRepo;

    @PostMapping("/save")
    public ResponseEntity<?> saveSubCategory(@RequestBody SaveSubCategoryDTO saveSubCategory) {
        return new ResponseEntity<>(subCategoryService.saveSubCategory(saveSubCategory), HttpStatus.OK);
    }

    @PutMapping("/update/{subCategoryId}")
    public ResponseEntity<?> updateSubCategory(@RequestBody SaveSubCategoryDTO saveSubCategoryDTO, @PathVariable String subCategoryId) {
        return new ResponseEntity<>(subCategoryService.updateSubCategory(saveSubCategoryDTO, subCategoryId), HttpStatus.OK);
    }

    @GetMapping("/get-sub-category-by-name/{subCategoryName}")
    public ResponseEntity<?> getSubCategoryByName(@PathVariable String subCategoryName) {
        return new ResponseEntity<>(subCategoryService.getSubCategoryByName(subCategoryName), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllSubCategory() {
        return new ResponseEntity<>(subCategoryService.getAllSubCategory(), HttpStatus.OK);
    }

    @DeleteMapping("/delete-sub-category/{subCategoryId}")
    public ResponseEntity<?> deleteSubCategory(@PathVariable String subCategoryId) {
        subCategoryService.deleteSubCategory(subCategoryId);
        return new ResponseEntity<>("Sub Category " + "/t" + subCategoryId + "/t" + "Deleted Successfully ", HttpStatus.OK);
    }

    @GetMapping("/get-all-subcategories")
    public ResponseEntity<?> getAllSubCategories() {
        return new ResponseEntity<>(subCategoryService.getAllSubCategories(), HttpStatus.OK);

    }
}