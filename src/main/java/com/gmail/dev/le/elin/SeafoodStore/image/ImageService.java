package com.gmail.dev.le.elin.SeafoodStore.image;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gmail.dev.le.elin.SeafoodStore.exception.BadRequestException;
import com.gmail.dev.le.elin.SeafoodStore.exception.ResourceNotFoundException;
import com.gmail.dev.le.elin.SeafoodStore.product.entity.Product;
import com.gmail.dev.le.elin.SeafoodStore.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;

    private final ImageMapper imageMapper;

    public ImageResponse saveImage(ImageRequest request, int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm"));

        Image image = new Image();
        image.setProduct(product);
        image.setPublicId(request.getPublicId());
        image.setSecureUrl(request.getSecureUrl());

        imageRepository.save(image);

        return imageMapper.toResponse(image);
    }

    public List<Image> saveImages(List<ImageRequest> requests, int productId) {
        if (requests == null || requests.isEmpty()) {
            return List.of();
        }
        if (requests.size() > 3) {
            throw new BadRequestException("Yêu cầu không hợp lệ, chỉ được lưu tối đa 3 ảnh cho mỗi sản phẩm");
        }
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm"));

        return requests.stream()
                .map((request) -> saveImageWithoutCheckProductValidation(request, product))
                .toList();
    }

    public Image saveImageWithoutCheckProductValidation(ImageRequest request, Product product) {
        Image image = new Image();
        image.setProduct(product);
        image.setPublicId(request.getPublicId());
        image.setSecureUrl(request.getSecureUrl());

        imageRepository.save(image);

        return image;
    }
}
