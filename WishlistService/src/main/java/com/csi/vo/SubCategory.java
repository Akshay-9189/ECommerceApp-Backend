package com.csi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategory {

    private String subCategoryId;

    private String subCategoryName;

    private Category category;
}
