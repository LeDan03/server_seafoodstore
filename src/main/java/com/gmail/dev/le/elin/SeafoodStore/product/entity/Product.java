package com.gmail.dev.le.elin.SeafoodStore.product.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gmail.dev.le.elin.SeafoodStore.category.Category;
import com.gmail.dev.le.elin.SeafoodStore.image.Image;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private BigDecimal minPrice;

    // Nếu chỉ có minPrice thì minPrice chính là giá của sản phẩm, còn nếu có maxPrice thì minPrice là giá thấp nhất và maxPrice là giá cao nhất của sản phẩm
    @Column()
    private BigDecimal maxPrice;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column()
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private int MOQ; // Minimum Order Quantity - Số lượng đặt hàng tối thiểu

    @ManyToOne
    @JoinColumn(name = "product_type_id", nullable = false)
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    
    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Image> images;
}
