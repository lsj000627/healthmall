package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    // ✅ [1] 이메일로 userId 조회
    @GetMapping("/id")
    public Long getUserId(@RequestParam String email) {
        UserDto user = userService.findByEmail(email);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다.");
        }
        return user.getId();
    }

    // ✅ [2] 로그인 처리 (세션 저장 포함)
    @PostMapping("/login")
    public String login(@RequestBody UserDto userDto, HttpSession session) {
        UserDto user = userService.findByEmail(userDto.getEmail());

        if (user == null || !user.getPassword().equals(userDto.getPassword())) {
            return "로그인 실패";
        }

        // ✅ 세션에 로그인 정보 저장
        session.setAttribute("loginUserId", user.getId());
        return "로그인 성공";
    }

    // ✅ [3] 로그인 상태 확인용 (테스트용)
    @GetMapping("/session-check")
    public String checkSession(HttpSession session) {
        Object userId = session.getAttribute("loginUserId");
        return (userId != null) ? "현재 로그인된 ID: " + userId : "로그인되지 않음";
    }

    // ✅ [4] 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 삭제
        return "index"; // templates/index.html 보여주기
    }


}
