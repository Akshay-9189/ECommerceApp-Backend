package com.fullstack.repo;

import com.fullstack.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SubCategoryRepo extends JpaRepository<SubCategory, String> {

    SubCategory findBySubCategoryName(String subCategoryName);
}
