package com.santana.java.back.end.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {
    @NotBlank
    private String productIdentifier;
    @NotBlank
    private String name;
    private String description;
    @NotNull
    private Float price;
    @NotNull
    private CategoryDTO categoryDTO;
}
