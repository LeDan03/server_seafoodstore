package com.gmail.dev.le.elin.SeafoodStore.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gmail.dev.le.elin.SeafoodStore.exception.BadRequestException;
import com.gmail.dev.le.elin.SeafoodStore.exception.ConflictException;
import com.gmail.dev.le.elin.SeafoodStore.product.dto.ProductTypeRequest;
import com.gmail.dev.le.elin.SeafoodStore.product.dto.ProductTypeResponse;
import com.gmail.dev.le.elin.SeafoodStore.product.entity.ProductType;
import com.gmail.dev.le.elin.SeafoodStore.product.mapper.ProductTypeMapper;
import com.gmail.dev.le.elin.SeafoodStore.product.repository.ProductTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductTypeService {
    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeMapper productTypeMapper;

    public ProductTypeResponse createProductType(ProductTypeRequest request) {
        if (request == null) {
            throw new BadRequestException("Yêu cầu không hợp lệ");
        }
        if (productTypeRepository.existsByName(request.getName())) {
            throw new ConflictException("Tên loại sản phẩm đã được tạo trước đó");
        }
        ProductType productType = new ProductType();
        productType.setName(request.getName());
        productType.setDescription(request.getDescription());

        productTypeRepository.save(productType);

        return productTypeMapper.toResponse(productType);
    }

    public List<ProductTypeResponse> findAllProductType() {
        return productTypeMapper.toResponses(productTypeRepository.findAll());
    }
}
