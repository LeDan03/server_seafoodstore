package com.gmail.dev.le.elin.SeafoodStore.product.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gmail.dev.le.elin.SeafoodStore.product.dto.ProductTypeResponse;
import com.gmail.dev.le.elin.SeafoodStore.product.entity.ProductType;

@Component
public class ProductTypeMapper {

    public ProductTypeResponse toResponse(ProductType productType) {
        return ProductTypeResponse.builder()
                .id(productType.getId())
                .name(productType.getName())
                .description(productType.getDescription())
                .build();
    }

    public List<ProductTypeResponse> toResponses(List<ProductType> types) {
        if (types == null || types.isEmpty()) {
            return List.of();
        }
        return types.stream()
                .map(this::toResponse)
                .toList();
    }
}
