package com.gmail.dev.le.elin.SeafoodStore.address;

import com.gmail.dev.le.elin.SeafoodStore.user.User;
import com.gmail.dev.le.elin.SeafoodStore.ward.Ward;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String detailAddress; //số nhà, tên đường

    @ManyToOne
    @JoinColumn(name = "ward_id", nullable = false)
    private Ward ward;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //địa chỉ này thuộc về người dùng nào
}
