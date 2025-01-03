package org.example.testtddtester.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.testtddtester.dto.product.ProductDto;
import org.example.testtddtester.dto.product.ProductMapper;
import org.example.testtddtester.entity.ProductEntity;
import org.example.testtddtester.repository.ProductRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(productMapper::productEntityToProductDto).toList();
    }

    @Override
    public ProductDto findById(Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(
                () -> new EmptyResultDataAccessException("Product: " + id + " not found", 1));
        return productMapper.productEntityToProductDto(product);
    }

    @Override
    public ProductDto save(ProductDto product) {
        ProductEntity entity = productMapper.productToProductEntity(product);
        ProductEntity savedEntity = productRepository.save(entity);
        return productMapper.productEntityToProductDto(savedEntity);
    }

    @Override
    public void delete(ProductDto product) {
        ProductEntity entity = productRepository.findById(product.getId()).orElseThrow(
                () -> new EmptyResultDataAccessException("Product: " + product.getId() + " not found", 1));
        productRepository.delete(entity);
    }
}