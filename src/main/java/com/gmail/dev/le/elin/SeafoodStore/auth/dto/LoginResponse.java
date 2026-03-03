package com.gmail.dev.le.elin.SeafoodStore.auth.dto;

import com.gmail.dev.le.elin.SeafoodStore.user.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LoginResponse {
    
    private String accessToken;
    private UserDto user;
}
