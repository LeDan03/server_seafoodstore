package com.gmail.dev.le.elin.SeafoodStore.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.dev.le.elin.SeafoodStore.auth.AuthResult;
import com.gmail.dev.le.elin.SeafoodStore.auth.dto.AuthResponse;
import com.gmail.dev.le.elin.SeafoodStore.auth.dto.LoginRequest;
import com.gmail.dev.le.elin.SeafoodStore.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(LoginRequest loginRequest) {
        AuthResult result = authService.login(loginRequest);
        return ResponseEntity.ok(AuthResponse.builder().accessToken(result.getAccessToken()).build());
    }
}
