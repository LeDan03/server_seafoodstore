package com.gmail.dev.le.elin.SeafoodStore.auth.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gmail.dev.le.elin.SeafoodStore.auth.LoginResult;
import com.gmail.dev.le.elin.SeafoodStore.auth.dto.LoginRequest;
import com.gmail.dev.le.elin.SeafoodStore.auth.dto.RegisterRequest;
import com.gmail.dev.le.elin.SeafoodStore.exception.BadRequestException;
import com.gmail.dev.le.elin.SeafoodStore.exception.ConflictException;
import com.gmail.dev.le.elin.SeafoodStore.exception.ResourceNotFoundException;
import com.gmail.dev.le.elin.SeafoodStore.exception.UnauthorizedException;
import com.gmail.dev.le.elin.SeafoodStore.refreshtoken.RefreshToken;
import com.gmail.dev.le.elin.SeafoodStore.refreshtoken.RefreshTokenRepository;
import com.gmail.dev.le.elin.SeafoodStore.role.RoleService;
import com.gmail.dev.le.elin.SeafoodStore.security.JwtService;
import com.gmail.dev.le.elin.SeafoodStore.security.Sha256RefreshEncoder;
import com.gmail.dev.le.elin.SeafoodStore.user.User;
import com.gmail.dev.le.elin.SeafoodStore.user.UserMapper;
import com.gmail.dev.le.elin.SeafoodStore.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private final PasswordEncoder passwordEncoder;
    private final Sha256RefreshEncoder sha256RefreshEncoder;
    private final UserMapper userMapper;

    private final JwtService jwtService;
    private final RoleService roleService;

    public String hashPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

    public LoginResult login(LoginRequest request) {
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Thông tin đăng nhập không chính xác"));

        if (!verifyPassword(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Thông tin đăng nhập không chính xác");
        }

        String accessToken = jwtService.generateAccessToken(user.getRole().getId(), user.getId(), user.getUsername());
        String refreshTokenRaw = jwtService.generateRefreshToken(user.getId());

        refreshTokenRepository.save(new RefreshToken(sha256RefreshEncoder.encode(refreshTokenRaw), user));

        return LoginResult.builder()
                .accessToken(accessToken)
                .refreshTokenRaw(refreshTokenRaw)
                .user(userMapper.toDto(user))
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

    public void logout(String refreshTokenRaw) {
        String encodedToken = sha256RefreshEncoder.encode(refreshTokenRaw);
        RefreshToken token = refreshTokenRepository.findByToken(encodedToken)
                .orElseThrow(() -> new ResourceNotFoundException("Refresh token không hợp lệ"));
        refreshTokenRepository.delete(token);
    }

    public String refreshAccessToken(String refreshTokenRaw) {
        String encoded = sha256RefreshEncoder.encode(refreshTokenRaw);
        RefreshToken rf_token = refreshTokenRepository.findByToken(encoded)
                .orElseThrow(() -> new UnauthorizedException("Refresh token không hợp lệ"));

        if(rf_token.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(rf_token);
            throw new UnauthorizedException("Refresh token đã hết hạn");
        }
        
        User user = rf_token.getUser();
        return jwtService.generateAccessToken(user.getRole().getId(), user.getId(), user.getUsername());
    }
}
