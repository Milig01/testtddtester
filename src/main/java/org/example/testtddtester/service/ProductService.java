package org.example.testtddtester.service;

import org.example.testtddtester.dto.product.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
    ProductDto findById(Long id);
    ProductDto save(ProductDto product);
    void delete(ProductDto product);
}