package com.gmail.dev.le.elin.SeafoodStore.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductTypeRequest {
    private String name;
    private String description;
}
