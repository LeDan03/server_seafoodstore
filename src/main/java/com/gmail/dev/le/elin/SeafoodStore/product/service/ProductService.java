package com.gmail.dev.le.elin.SeafoodStore.product.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gmail.dev.le.elin.SeafoodStore.category.Category;
import com.gmail.dev.le.elin.SeafoodStore.category.CategoryRepository;
import com.gmail.dev.le.elin.SeafoodStore.exception.BadRequestException;
import com.gmail.dev.le.elin.SeafoodStore.exception.ConflictException;
import com.gmail.dev.le.elin.SeafoodStore.exception.ResourceNotFoundException;
import com.gmail.dev.le.elin.SeafoodStore.image.ImageService;
import com.gmail.dev.le.elin.SeafoodStore.product.dto.ProductRequest;
import com.gmail.dev.le.elin.SeafoodStore.product.dto.ProductResponse;
import com.gmail.dev.le.elin.SeafoodStore.product.dto.ProductTypeRequest;
import com.gmail.dev.le.elin.SeafoodStore.product.dto.ProductTypeResponse;
import com.gmail.dev.le.elin.SeafoodStore.product.entity.Product;
import com.gmail.dev.le.elin.SeafoodStore.product.entity.ProductType;
import com.gmail.dev.le.elin.SeafoodStore.product.mapper.ProductMapper;
import com.gmail.dev.le.elin.SeafoodStore.product.mapper.ProductTypeMapper;
import com.gmail.dev.le.elin.SeafoodStore.product.repository.ProductRepository;
import com.gmail.dev.le.elin.SeafoodStore.product.repository.ProductTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ImageService imageService;

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final ProductMapper productMapper;

    private final ProductTypeRepository productTypeRepository;

    public ProductResponse createProduct(ProductRequest request) {
        if (request == null) {
            throw new BadRequestException("");
        }
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(""));
        ProductType productType = productTypeRepository.findById(request.getProductTypeId())
                .orElseThrow(() -> new ResourceNotFoundException(""));

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCreatedAt(LocalDateTime.now());
        product.setMOQ(request.getMOQ());
        product.setMinPrice(request.getMinPrice());
        product.setMaxPrice(request.getMaxPrice());

        product.setCategory(category);
        product.setProductType(productType);

        productRepository.save(product);
        product.setImages(imageService.saveImages(request.getImageRequests(), product.getId()));
        productRepository.save(product);

        return productMapper.toResponse(product);
    }

    public List<ProductResponse> findAll() {
        return productMapper.toResponses(productRepository.findAll());
    }
}
