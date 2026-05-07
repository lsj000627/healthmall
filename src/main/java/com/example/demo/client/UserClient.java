package com.example.demo.client;

import com.example.demo.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "userClient", url = "http://223.130.153.51:30909")
public interface UserClient {

    @GetMapping("/users")
    List<UserDto> getAllUsers();

    @PostMapping("/users/signup")
    String createUser(@RequestBody UserDto userDto);

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id);

    @PutMapping("/users/{id}")
    String updateUser(@PathVariable Long id, @RequestBody UserDto userDto);

    // ✅ 로그인 기능 추가
    @PostMapping("/users/login")
    String login(@RequestBody UserDto userDto);
}
