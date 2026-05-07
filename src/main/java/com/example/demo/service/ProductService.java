package com.example.demo.service;

import com.example.demo.client.ProductClient;
import com.example.demo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductClient productClient;

    public ProductService(ProductClient productClient) {
        this.productClient = productClient;
    }

    // ✅ 전체 상품 조회
    public List<Product> findAll() {
        return productClient.getAllProducts();
    }

    // ✅ 단일 상품 조회
 // 수정된 코드 (예외 처리 추가!)
    public Product findById(Long id) {
        try {
            Product product = productClient.getProductById(id);
            System.out.println("✅ 상품 조회 성공: " + product.getName());
            return product;
        } catch (Exception e) {
            System.out.println("❌ 상품 조회 실패 - ID: " + id);
            e.printStackTrace();  // ← 콘솔에서 오류 로그 확인할 수 있음
            return null;
        }
    }



 // ✅ 상품 등록
    public Product add(Product product) {
        product.setId(null);  // 🔧 DB에서 자동 생성되도록 강제로 null 처리
        return productClient.createProduct(product);
    }


    // ✅ 상품 수정
    public Product update(Long id, Product product) {
        return productClient.updateProduct(id, product);
    }

    // ✅ 상품 삭제
    public void deleteById(Long id) {
        productClient.deleteProduct(id);
    }
}
