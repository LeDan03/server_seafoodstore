package com.gmail.dev.le.elin.SeafoodStore.address;

import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    
    public AddressDto toDto(Address address) {
        if (address == null) {
            return null;
        }

        return AddressDto.builder()
                .id(address.getId())
                .detailAddress(address.getDetailAddress())
                .wardCode(address.getWard().getCode() != null ? address.getWard().getCode() : null)
                .userId(address.getUser().getId())
                .build();
    }
}
