package org.example.testtddtester.service;

import org.example.testtddtester.dto.product.ProductDto;
import org.example.testtddtester.dto.product.ProductMapper;
import org.example.testtddtester.entity.ProductEntity;
import org.example.testtddtester.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
    @Mock
    private static ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    @Mock
    private static ProductMapper productMapper = Mockito.mock(ProductMapper.class);

    private static final ProductEntity productEntity =
            new ProductEntity(1L, "тестер", "тестер", 10, 5);

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void testFindAll() {
        ProductEntity productEntity = new ProductEntity();
        ProductDto productDto = new ProductDto();
        List<ProductEntity> products = List.of(productEntity);
        Mockito.when(productRepository.findAll()).thenReturn(products);
        Mockito.when(productMapper.productEntityToProductDto(productEntity)).thenReturn(productDto);
        List<ProductDto> list = productService.findAll();
        assertEquals(list.size(), products.size());
        assertEquals(list.get(0), productDto);
    }

    @Test
    public void testFindById() {
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));
        Mockito.when(productRepository.findById(2L)).thenReturn(Optional.empty());
        ProductDto productDto = new ProductDto();
        Mockito.when(productMapper.productEntityToProductDto(productEntity)).thenReturn(productDto);
        ProductDto product = productService.findById(1L);
        assertEquals(productDto, product);
        assertThrows(EmptyResultDataAccessException.class, () -> productService.findById(2L));
    }

    @Test
    public void testSave() {
        Mockito.when(productRepository.save(productEntity)).thenReturn(productEntity);
        ProductDto productDto = new ProductDto();
        Mockito.when(productMapper.productToProductEntity(productDto)).thenReturn(productEntity);
        Mockito.when(productMapper.productEntityToProductDto(productEntity)).thenReturn(productDto);
        ProductDto product = productService.save(productDto);
        assertEquals(productDto, product);
    }

    @Test
    public void testDelete() {
        ProductDto productDto = new ProductDto(1L, "тестер", "тестер", 10, 5);
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));
        productService.delete(productDto);
        Mockito.verify(productRepository, Mockito.times(1)).delete(productEntity);
    }
}