package com.gmail.dev.le.elin.SeafoodStore.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);

}
