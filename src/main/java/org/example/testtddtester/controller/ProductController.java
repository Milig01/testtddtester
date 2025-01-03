package org.example.testtddtester.controller;

import lombok.RequiredArgsConstructor;
import org.example.testtddtester.dto.product.ProductDto;
import org.example.testtddtester.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    @DeleteMapping
    public void deleteProduct(@RequestBody ProductDto productDto) {
        productService.delete(productDto);
    }
}