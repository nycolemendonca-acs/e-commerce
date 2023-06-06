package com.santana.java.back.end.dto;

import com.santana.java.back.end.model.Category;
import com.santana.java.back.end.model.Product;

public class DTOConverter {
    public static ProductDTO convert(Product product) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setProductIdentifier(product.getProductIdentifier());
        productDTO.setDescription(product.getDescription());

        if (product.getCategory() != null) productDTO.setCategory(CategoryDTO.convert(product.getCategory()));

        return productDTO;
    }

    public static CategoryDTO convert(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());

        return categoryDTO;
    }
}
