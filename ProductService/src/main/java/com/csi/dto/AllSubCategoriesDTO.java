package com.csi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllSubCategoriesDTO {

    private String categoryName;

    private String subCategoryId;

    private String subCategoryName;
}
