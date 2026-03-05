package com.gmail.dev.le.elin.SeafoodStore.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ImageResponse {
    private long id;

    private String secureUrl;
    private int productId;
}
