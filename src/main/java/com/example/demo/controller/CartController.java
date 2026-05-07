package com.example.demo.controller;

import com.example.demo.client.CartClient;
import com.example.demo.dto.CartDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartClient cartClient;

    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) {
            return "redirect:/login";
        }
        String userId = userIdObj.toString();
        List<CartDto> cartItems = cartClient.getCartItems(userId);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("userId", userId);
        return "cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(@ModelAttribute CartDto cartDto, HttpSession session) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj != null) {
            cartDto.setUserId(userIdObj.toString());
        } else {
            cartDto.setUserId("guest");
        }
        cartClient.addToCart(cartDto);
        return "redirect:/cart";
    }

    @PostMapping("/cart/delete/{id}")
    public String removeItem(@PathVariable Long id, HttpSession session) {
        Object userIdObj = session.getAttribute("userId");
        String userId = userIdObj != null ? userIdObj.toString() : "guest";
        cartClient.removeFromCart(id);
        return "redirect:/cart";
    }
}
