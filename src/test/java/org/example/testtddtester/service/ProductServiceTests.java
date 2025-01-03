package org.example.testtddtester;

import org.example.testtddtester.entity.ProductEntity;
import org.example.testtddtester.repository.ProductRepository;
import org.example.testtddtester.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
    @Mock
    private static ProductRepository productRepository = Mockito.mock(ProductRepository.class);

    private static final List<ProductEntity> products = List.of(new ProductEntity(), new ProductEntity());

    private static final ProductEntity productEntity =
            new ProductEntity(1, "тестер", "тестер", 10, 5);

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeAll
    public static void init() {
        Mockito.when(productRepository.findAll()).thenReturn(products);
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));
        Mockito.when(productRepository.findById(2L)).thenThrow();
        Mockito.when(productRepository.save(productEntity)).thenReturn(productEntity);
    }

    @Test
    public void testFindAll() {
        List<ProductEntity> list = productService.findAll();
        assertEquals(list.size(), products.size());
    }

    @Test
    public void testFindById() {
        ProductEntity product = productService.findById(1L);
        assertEquals(productEntity, product);
        assertThrows(RuntimeException.class, () -> productService.findById(2L));
    }

    @Test
    public void testSave() {
        ProductEntity product = productService.save(productEntity);
        assertEquals(productEntity, product);
    }

    @Test
    public void testDelete() {
        productService.delete(productEntity);
        Mockito.verify(productRepository, Mockito.times(1)).delete(productEntity);
    }
}