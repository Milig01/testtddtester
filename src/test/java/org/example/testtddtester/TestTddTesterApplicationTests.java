package org.example.testtddtester;

import org.example.testtddtester.dto.product.ProductDto;
import org.example.testtddtester.entity.ProductEntity;
import org.example.testtddtester.repository.ProductRepository;
import org.example.testtddtester.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@ActiveProfiles("mytest")
class TestTddTesterApplicationTests {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @Test
    void contextLoads() {}

    @BeforeEach
    void setUp() {
        ProductEntity entity = new ProductEntity(1L, "test", "test", 5, 10);
        productRepository.save(entity);
    }

    @Test
    void test() {
        productService.delete(new ProductDto(1L, "test", "test", 5, 10));
        List<ProductEntity> list = productRepository.findAll();
        System.out.println(list.size());
    }
}