package org.example.testtddtester.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Этот дто используется во всех запросах api/product
 * @since 1.0
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Integer quantity;
}