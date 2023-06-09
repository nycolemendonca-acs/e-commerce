package com.santana.java.back.end.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.autoconfigure.webservices.client.MockWebServiceServerAutoConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    public static MockWebServier mockBackEnd;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() throws IOException {
        mockBackEnd = new MockWebServiceServerAutoConfiguration();
        mockBackEnd.start();

        String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());
        ReflectionTestUtils.setField(productService, "productApiURL", baseUrl);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    void test_getProductByIdentifier() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setPreco(1000F);
        productDTO.setProductIdentifier("prod-identifier");

        ObjectMapper objectMapper = new ObjectMapper();

        mockBackEnd.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(productDTO))
                .addHeader("Content-Type", "application/json"));

        productDTO = productService.getProductByIdentifier("prod-identifier");
        Assertions.assertEquals(1000F, productDTO.getPrice());
        Assertions.assertEquals("prod-identifier", productDTO.getProductIdentifier());
    }
}


