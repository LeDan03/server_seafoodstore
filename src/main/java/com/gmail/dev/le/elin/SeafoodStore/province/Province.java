package com.gmail.dev.le.elin.SeafoodStore.province;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Province {
    
    @Id
    private String code;

    @Column(nullable = false)
    private String name;

    @Column
    private String type; //tỉnh/thành phố

}
