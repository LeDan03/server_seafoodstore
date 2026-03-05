package com.gmail.dev.le.elin.SeafoodStore.image;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ImageMapper {

    public ImageResponse toResponse(Image image) {
        return ImageResponse.builder()
                .id(image.getId())
                .secureUrl(
                        image.getSecureUrl())
                .productId(image.getProduct().getId())
                .build();
    }

    public List<ImageResponse> toResponses(List<Image> images) {
        if (images == null || images.isEmpty()) {
            return List.of();
        }
        return images.stream()
                .map(this::toResponse)
                .toList();
    }
}
