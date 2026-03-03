package com.gmail.dev.le.elin.SeafoodStore.user;

import java.util.List;

import com.gmail.dev.le.elin.SeafoodStore.address.AddressDto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {
    
    private Integer id;
    private String username;
    private String email;
    private String phone;
    private List<AddressDto> addresses;
}
