package com.gmail.dev.le.elin.SeafoodStore.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class AuthResult {
    
    private String accessToken;
    private String refreshTokenRaw;
}
