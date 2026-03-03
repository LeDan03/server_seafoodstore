package com.gmail.dev.le.elin.SeafoodStore.ward;

import com.gmail.dev.le.elin.SeafoodStore.province.Province;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ward {
    
    @Id
    private String code;

    @Column(nullable = false)
    private String name;

    @Column
    private String type; //phường/xã/thị trấn

    @ManyToOne
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;
}
