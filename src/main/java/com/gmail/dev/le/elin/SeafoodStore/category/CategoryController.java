package com.gmail.dev.le.elin.SeafoodStore.category;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.dev.le.elin.SeafoodStore.common.response.ApiResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryDto>> createCategory(@RequestBody String categoryName) {

        CategoryDto newCate = categoryService.createCategory(categoryName);
        return ResponseEntity.ok().body(ApiResponse.success(newCate, "Đã tạo danh mục: " + newCate.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCategoryById(@PathVariable int id) {
        categoryService.deleteCategory(id);

        return ResponseEntity.ok().body(ApiResponse.success("", "Đã xóa danh mục"));
    }

}
