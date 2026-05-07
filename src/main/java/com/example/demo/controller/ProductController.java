package com.example.demo.controller;

import com.example.demo.client.CartClient;
import com.example.demo.dto.CartDto;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private final CartClient cartClient;

    public ProductController(ProductService productService, CartClient cartClient) {
        this.productService = productService;
        this.cartClient = cartClient;
    }

    // ✅ 상품 목록 보기
    @GetMapping("/products")
    public String listProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "product-list"; // templates/product-list.html
    }

    // ✅ 장바구니 담기
    @PostMapping("/products/cart/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam(defaultValue = "1") int quantity,
                            HttpSession session) {
        String userId = getUserId(session);  // 세션 기반 유저 ID
        CartDto cartDto = new CartDto(productId, quantity, userId);
        cartClient.addToCart(cartDto);
        return "redirect:/products";
    }

    // ✅ 상품 등록 폼
    @GetMapping("/products/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());  // ✅ 바인딩 객체 등록
        return "add_product";
    }

    
   
    // ✅ 상품 등록 처리
    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute Product product) {
        System.out.println("등록 값 확인: "
            + product.getName() + ", "
            + product.getPrice() + ", "
            + product.getCategory() + ", "
            + product.getImage()); // 👈 이 줄

        productService.add(product);
        return "redirect:/products";
    }



    // ✅ 상품 수정 폼
    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);

        // 🔧 상품이 없더라도 빈 객체로 대체 (폼은 열리게 하기 위함)
        if (product == null) {
            product = new Product(); // 기본값으로 초기화
        }

        File imageFolder = new File("src/main/resources/static/images");
        String[] imageNames = imageFolder.list((dir, name) ->
                name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png")
        );

        model.addAttribute("imageList", Arrays.asList(imageNames));
        model.addAttribute("product", product);
        return "edit_product";
    }



    // ✅ 상품 수정 처리
    @PostMapping("/products/edit/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        productService.update(id, product);
        return "redirect:/products";
    }

    // ✅ 상품 삭제 처리
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

    // ✅ 세션에서 userId 가져오기 (없으면 guest 처리)
    private String getUserId(HttpSession session) {
        Object userIdObj = session.getAttribute("loginUserId");
        return (userIdObj != null) ? userIdObj.toString() : "guest";
    }
}
