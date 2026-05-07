package com.example.demo.client;

import com.example.demo.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product-api", url = "http://223.130.162.28:30908/products")
public interface ProductClient {

    // ✅ 전체 조회
    @GetMapping
    List<Product> getAllProducts();

    // ✅ 단일 조회
    @GetMapping("/{id}")
    Product getProductById(@PathVariable("id") Long id);

    // ✅ 등록
    @PostMapping
    Product createProduct(@RequestBody Product product);

    // ✅ 수정
    @PutMapping("/{id}")
    Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product);

    // ✅ 삭제
    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable("id") Long id);
}
