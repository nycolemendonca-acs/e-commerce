package com.santana.java.back.end.service;

import com.santana.java.back.end.dto.ProductDTO;
import com.santana.java.back.end.model.Product;
import com.santana.java.back.end.repository.ProductRepository;
import com.santana.java.back.end.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ProductService {
    private ProductRepository productRepository;
    // private CategoryRepository categoryRepository;


    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.findAll();

        return products
                .stream()
                .map(ProductDTO::convert)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductByCategoryId(Long categoryId) {
        List<Product> products = productRepository.getProductByCategory(categoryId);

        return products
                .stream()
                .map(ProductDTO::convert)
                .collect(Collectors.toList());
    }

    public ProductDTO findByProductIdentifier(String productIdentifier) {
        Product product = productRepository.findByProductIdentifier(productIdentifier);

        if (product != null) return ProductDTO.convert(product);

        return null;
    }

    public ProductDTO save(ProductDTO productDTO) {
        Product product = productRepository.save(Product.convert(productDTO));

        return ProductDTO.convert(product);
    }

    public void delete(long productId) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) productRepository.delete(product.get());
    }

    public ProductDTO editProduct(long id, ProductDTO dto) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (dto.getName() != null || !dto.getName().isEmpty()) product.setName(dto.getName());
        if (dto.getPrice() != null) product.setPrice(dto.getPrice());

        return ProductDTO.convert(productRepository.save(product));
    }

    public Page<ProductDTO> getAllPage(Pageable page) {
        Page<Product> users = productRepository.findAll(page);

        return users.map(ProductDTO::convert);
    }
}