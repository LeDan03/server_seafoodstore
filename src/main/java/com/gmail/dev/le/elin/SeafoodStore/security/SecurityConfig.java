package com.gmail.dev.le.elin.SeafoodStore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // Tắt CSRF vì dùng JWT (stateless)
            .csrf(csrf -> csrf.disable())

            // Không dùng session
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // Phân quyền
            .authorizeHttpRequests(auth -> auth

                // Public APIs
                .requestMatchers(
                        "/auth/**",
                        "/products/**",
                        "/categories/**",
                        "/images/**"
                ).permitAll()

                // ADMIN
                .requestMatchers("/admin/**")
                .hasRole("ADMIN")

                // STAFF hoặc ADMIN
                .requestMatchers("/staff/**")
                .hasAnyRole("ADMIN", "STAFF")

                // Các API khác yêu cầu login
                .anyRequest().authenticated()
            );

        // Thêm JWT filter trước UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}