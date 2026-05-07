package com.example.demo.client;

import com.example.demo.dto.CartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "cart", url = "http://223.130.153.51:30907")
public interface CartClient {

    @GetMapping("/cart")
    List<CartDto> getCartItems(@RequestParam("userId") String userId);

    @PostMapping("/cart")
    void addToCart(@RequestBody CartDto cartDto);

    @DeleteMapping("/cart/{id}")
    void removeFromCart(@PathVariable Long id);
}
