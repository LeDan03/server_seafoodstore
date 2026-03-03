package com.gmail.dev.le.elin.SeafoodStore.user;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gmail.dev.le.elin.SeafoodStore.address.AddressMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final AddressMapper addressMapper;

    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhoneNumber())
                .roleName(user.getRole().getName())
                .addresses(user.getAddresses() != null ? user.getAddresses().stream().map(addressMapper::toDto).toList() : null)
                .build();
    }
    public List<UserDto> toDtoList(List<User> users) {
        return users.stream().map(this::toDto).toList();
    }
}
