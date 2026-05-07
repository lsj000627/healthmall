package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // 🔍 제품명 검색 메서드
    List<Product> findByNameContainingIgnoreCase(String keyword);
}
