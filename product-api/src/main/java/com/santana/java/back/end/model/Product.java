package com.santana.java.back.end.model;

import com.santana.java.back.end.dto.ProductDTO;
import com.santana.java.back.end.dto.CategoryDTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String name;
    private Float price;
    private String description;
    private String product_identifier;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public static Product convert(ProductDTO productDTO) {
        Product product = new Product();

        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setProduct_identifier(productDTO.getProductIdentifier());

        product.setCategory(Category.convert(productDTO.getCategory()));

        return product;
    }
}
