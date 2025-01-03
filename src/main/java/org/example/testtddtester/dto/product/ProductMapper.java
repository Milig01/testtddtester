package org.example.testtddtester.dto.product;

import org.example.testtddtester.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductEntity productToProductEntity(ProductDto productDto);

    ProductDto productEntityToProductDto(ProductEntity productEntity);
}