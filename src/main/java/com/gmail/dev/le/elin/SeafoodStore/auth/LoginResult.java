package com.gmail.dev.le.elin.SeafoodStore.auth;

import com.gmail.dev.le.elin.SeafoodStore.user.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class LoginResult {
    
    private String accessToken;
    private String refreshTokenRaw;

    private UserDto user;
}
