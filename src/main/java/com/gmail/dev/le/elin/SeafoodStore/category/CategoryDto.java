package com.gmail.dev.le.elin.SeafoodStore.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CategoryDto {

    private int id;
    private String name;
}
