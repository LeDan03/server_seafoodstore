package com.gmail.dev.le.elin.SeafoodStore.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductTypeResponse {
    
    private int id;
    private String name;
    private String description;
    
}
