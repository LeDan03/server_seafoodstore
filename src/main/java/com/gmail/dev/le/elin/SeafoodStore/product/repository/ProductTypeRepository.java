package com.gmail.dev.le.elin.SeafoodStore.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmail.dev.le.elin.SeafoodStore.product.entity.ProductType;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer>{
   boolean existsByName(String name);
}
