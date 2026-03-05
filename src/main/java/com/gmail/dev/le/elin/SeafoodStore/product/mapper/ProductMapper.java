package com.gmail.dev.le.elin.SeafoodStore.product.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gmail.dev.le.elin.SeafoodStore.image.ImageMapper;
import com.gmail.dev.le.elin.SeafoodStore.product.dto.ProductResponse;
import com.gmail.dev.le.elin.SeafoodStore.product.entity.Product;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final ImageMapper imageMapper;

    public ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .MOQ(product.getMOQ())
                .minPrice(product.getMinPrice())
                .maxPrice(product.getMaxPrice())
                .categoryId(product.getCategory().getId())
                .productTypeId(product.getProductType().getId())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .images(product.getImages().isEmpty() || product.getImages() == null
                        ? List.of()
                        : imageMapper.toResponses(product.getImages()))
                .build();
    }

    public List<ProductResponse> toResponses(List<Product> products) {
        if (products == null || products.isEmpty()) {
            return List.of();
        }
        return products.stream()
                .map(this::toResponse)
                .toList();
    }
}
