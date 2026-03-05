package com.gmail.dev.le.elin.SeafoodStore.product.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.gmail.dev.le.elin.SeafoodStore.image.ImageResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductResponse {

    private int id;
    private String name;
    private String description;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private int MOQ;
    private int categoryId;
    private int productTypeId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<ImageResponse> images;
}
