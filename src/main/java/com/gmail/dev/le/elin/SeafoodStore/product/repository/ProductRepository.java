package com.gmail.dev.le.elin.SeafoodStore.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmail.dev.le.elin.SeafoodStore.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    
}
