package com.gmail.dev.le.elin.SeafoodStore.auth.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.dev.le.elin.SeafoodStore.auth.LoginResult;
import com.gmail.dev.le.elin.SeafoodStore.auth.dto.LoginResponse;
import com.gmail.dev.le.elin.SeafoodStore.auth.dto.LoginRequest;
import com.gmail.dev.le.elin.SeafoodStore.auth.service.AuthService;
import com.gmail.dev.le.elin.SeafoodStore.common.response.ApiResponse;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest,
            HttpServletResponse response) {
        LoginResult result = authService.login(loginRequest);

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", result.getRefreshTokenRaw())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(7 * 24 * 60 * 60)
                .sameSite("None")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
        return ResponseEntity.ok(ApiResponse.success(LoginResponse.builder()
                .accessToken(result.getAccessToken())
                .user(result.getUser())
                .build(), "Login successful"));
    }
}
