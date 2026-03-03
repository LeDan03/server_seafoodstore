package com.gmail.dev.le.elin.SeafoodStore.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gmail.dev.le.elin.SeafoodStore.auth.AuthResult;
import com.gmail.dev.le.elin.SeafoodStore.auth.dto.LoginRequest;
import com.gmail.dev.le.elin.SeafoodStore.auth.dto.RegisterRequest;
import com.gmail.dev.le.elin.SeafoodStore.exception.BadRequestException;
import com.gmail.dev.le.elin.SeafoodStore.exception.ConflictException;
import com.gmail.dev.le.elin.SeafoodStore.exception.ResourceNotFoundException;
import com.gmail.dev.le.elin.SeafoodStore.refreshtoken.RefreshToken;
import com.gmail.dev.le.elin.SeafoodStore.refreshtoken.RefreshTokenRepository;
import com.gmail.dev.le.elin.SeafoodStore.role.RoleService;
import com.gmail.dev.le.elin.SeafoodStore.security.JwtService;
import com.gmail.dev.le.elin.SeafoodStore.user.User;
import com.gmail.dev.le.elin.SeafoodStore.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final RoleService roleService;

    public String hashPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

    public AuthResult login(LoginRequest request) {
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Thông tin đăng nhập không chính xác"));

        if (!verifyPassword(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Thông tin đăng nhập không chính xác");
        }

        String accessToken = jwtService.generateAccessToken(user.getRole().getId(), user.getId(), user.getUsername());
        String refreshTokenRaw = jwtService.generateRefreshToken(user.getId());

        refreshTokenRepository.save(new RefreshToken(passwordEncoder.encode(refreshTokenRaw), user));

        return AuthResult.builder()
                .accessToken(accessToken)
                .refreshTokenRaw(refreshTokenRaw)
                .build();
    }

    public void register(RegisterRequest request) {
        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new ConflictException("Số điện thoại đã được sử dụng để đăng ký tài khoản");
        }
        if (request.getEmail() != null && !request.getEmail().isEmpty()
                && userRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException("Email đã được sử dụng cho tài khoản khác");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(hashPassword(request.getPassword()))
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .role(roleService.getRoleByName("ROLE_USER"))
                .build();
        userRepository.save(user);
    }

    public void revokeRefreshToken(String refreshTokenRaw) {
        
    }
}
