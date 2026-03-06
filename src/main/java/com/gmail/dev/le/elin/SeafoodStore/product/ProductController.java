package com.gmail.dev.le.elin.SeafoodStore.product;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.dev.le.elin.SeafoodStore.common.response.ApiResponse;
import com.gmail.dev.le.elin.SeafoodStore.product.dto.ProductRequest;
import com.gmail.dev.le.elin.SeafoodStore.product.dto.ProductResponse;
import com.gmail.dev.le.elin.SeafoodStore.product.dto.ProductTypeRequest;
import com.gmail.dev.le.elin.SeafoodStore.product.dto.ProductTypeResponse;
import com.gmail.dev.le.elin.SeafoodStore.product.service.ProductService;
import com.gmail.dev.le.elin.SeafoodStore.product.service.ProductTypeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;
    private final ProductTypeService productTypeService;

    @PostMapping("/types")
    public ResponseEntity<ApiResponse<ProductTypeResponse>> createProductType(@RequestBody ProductTypeRequest request){
        ProductTypeResponse response = productTypeService.createProductType(request);
        return ResponseEntity.ok().body(ApiResponse.created(response, "Đã tạo loại sản phẩm '"+ request.getName()+"'"));
    }

    @GetMapping("/types")
    public ResponseEntity<ApiResponse<List<ProductTypeResponse>>> findAllProductTypes(){
        List<ProductTypeResponse> response = productTypeService.findAllProductType();
        return ResponseEntity.ok().body(ApiResponse.success(response, "Đã lấy danh sách loại sản phẩm thành công"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(@RequestBody ProductRequest request){
        ProductResponse response = productService.createProduct(request);
        return ResponseEntity.ok().body(ApiResponse.created(response, "Đã tạo sản phẩm '"+ request.getName()+"'"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> findAll(){
        List<ProductResponse> response = productService.findAll();

        return ResponseEntity.ok().body(ApiResponse.success(response, "Lấy danh sách sản phẩm thành công"));
    }
}
