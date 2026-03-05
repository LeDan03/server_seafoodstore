package com.gmail.dev.le.elin.SeafoodStore.product.dto;

import java.math.BigDecimal;
import java.util.List;

import com.gmail.dev.le.elin.SeafoodStore.image.ImageRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private int MOQ;
    private int productTypeId;

    private List<ImageRequest> imageRequests;
}
