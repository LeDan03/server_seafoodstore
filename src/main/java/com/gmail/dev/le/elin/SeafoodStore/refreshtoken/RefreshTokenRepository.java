package com.gmail.dev.le.elin.SeafoodStore.refreshtoken;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    RefreshToken findByUserId(Long userId);
    Optional<RefreshToken> findByTokenHash(String tokenHash);
}
