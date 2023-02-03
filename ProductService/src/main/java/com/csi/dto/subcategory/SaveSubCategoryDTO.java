package com.csi.dto.subcategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveSubCategoryDTO {
    private String subCategoryName;
    private String categoryName;
}
