package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.demo.client") // ✅ Feign Client 패키지 명시
public class HealthmallUiApplication {
    public static void main(String[] args) {
        SpringApplication.run(HealthmallUiApplication.class, args);
    }
}
