package com.fullstack.repo;

import com.fullstack.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, String> {

    Category findByCategoryName(String categoryName);
}
