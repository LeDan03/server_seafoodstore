package com.gmail.dev.le.elin.SeafoodStore.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ImageRequest {
    private String publicId;
    private String secureUrl;
}
