package com.gmail.dev.le.elin.SeafoodStore.address;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddressDto {
    
    private Integer id;
    private String detailAddress;
    private String wardCode;
    private Integer userId;
}
