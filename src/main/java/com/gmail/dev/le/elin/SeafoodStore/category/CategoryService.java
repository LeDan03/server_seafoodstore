package com.gmail.dev.le.elin.SeafoodStore.category;

import org.springframework.stereotype.Service;

import com.gmail.dev.le.elin.SeafoodStore.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    public CategoryDto createCategory(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }
        if (categoryRepository.findByName(name).isPresent()) {
            throw new IllegalArgumentException("Đã tồn tại tên danh mục: " + name);
        }
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);

        return categoryMapper.toDto(category);
    }

    public void deleteCategory(int id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Không tìm thấy danh mục với id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
