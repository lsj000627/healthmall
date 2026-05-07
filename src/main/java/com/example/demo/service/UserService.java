package com.example.demo.service;

import com.example.demo.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    // 🔧 실제 DB 대신 임시 사용자 리스트 사용
    private static final List<UserDto> mockUsers = new ArrayList<>();

    static {
        UserDto user1 = new UserDto(1L, "홍길동", "test@example.com", "1234");
        UserDto user2 = new UserDto(2L, "이승재", "user2@example.com", "pass");
        mockUsers.add(user1);
        mockUsers.add(user2);
    }

    // ✅ 이메일로 사용자 검색
    public UserDto findByEmail(String email) {
        return mockUsers.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }
}
