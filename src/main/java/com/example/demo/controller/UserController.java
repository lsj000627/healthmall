package com.example.demo.controller;

import com.example.demo.client.UserClient;
import com.example.demo.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserClient userClient;

    // 회원 목록 페이지
    @GetMapping("/users")
    public String getUserList(Model model) {
        List<UserDto> users = userClient.getAllUsers();
        model.addAttribute("users", users);
        return "users";  // → templates/users.html 렌더링
    }

    // 회원 생성 요청 처리
    @PostMapping("/user-create")
    public String createUser(@RequestParam String name,
                             @RequestParam String email) {
        UserDto newUser = new UserDto();
        newUser.setName(name);
        newUser.setEmail(email);
        userClient.createUser(newUser);
        return "redirect:/users";
    }



    // 회원 삭제 요청 처리
    @PostMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userClient.deleteUser(id);
        return "redirect:/users";
    }

    // 회원 수정 요청 처리
    @PostMapping("/user-update")
    public String updateUser(@RequestParam Long id,
                             @RequestParam String name,
                             @RequestParam String email) {
        UserDto user = new UserDto();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        userClient.updateUser(id, user);
        return "redirect:/users";
    }
}
