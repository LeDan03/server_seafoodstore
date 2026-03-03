package com.gmail.dev.le.elin.SeafoodStore.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final String accessTokenSecret;
    private final long accessTokenExpiration;
    private final String refreshTokenSecret;
    private final long refreshTokenExpiration;

    public JwtService(
            @Value("${jwt.access.secret}") String accessTokenSecret,
            @Value("${jwt.access.expiration}") long accessTokenExpiration,
            @Value("${jwt.refresh.secret}") String refreshTokenSecret,
            @Value("${jwt.refresh.expiration}") long refreshTokenExpiration
    ) {
        this.accessTokenSecret = accessTokenSecret;
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenSecret = refreshTokenSecret;
        this.refreshTokenExpiration = refreshTokenExpiration;
    }

    // ===================== KEY =====================

    private Key getSigningKey(String secret) {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // ===================== GENERATE TOKEN =====================

    public String generateAccessToken(Integer roleId, Integer userId, String username) {
        return Jwts.builder()
                .setSubject(userId.toString()) // subject = userId (chuẩn JWT)
                .claim("roleId", roleId)
                .claim("username", username)
                .setIssuer("elin.le.dev")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .signWith(getSigningKey(accessTokenSecret))
                .compact();
    }

    public String generateRefreshToken(Integer userId) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuer("elin.le.dev")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(getSigningKey(refreshTokenSecret))
                .compact();
    }

    // ===================== VALIDATE =====================

    public boolean validateAccessToken(String token) {
        return validateToken(token, accessTokenSecret);
    }

    public boolean validateRefreshToken(String token) {
        return validateToken(token, refreshTokenSecret);
    }

    private boolean validateToken(String token, String secret) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey(secret))
                    .requireIssuer("elin.le.dev")
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (MalformedJwtException | SignatureException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    // ===================== EXTRACT =====================

    private Claims extractClaims(String token, String secret) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(secret))
                .requireIssuer("elin.le.dev")
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Integer extractUserIdFromAccessToken(String token) {
        return Integer.parseInt(
                extractClaims(token, accessTokenSecret).getSubject()
        );
    }

    public Integer extractRoleIdFromAccessToken(String token) {
        return extractClaims(token, accessTokenSecret)
                .get("roleId", Integer.class);
    }

    public String extractUsernameFromAccessToken(String token) {
        return extractClaims(token, accessTokenSecret)
                .get("username", String.class);
    }

    public Integer extractUserIdFromRefreshToken(String token) {
        return Integer.parseInt(
                extractClaims(token, refreshTokenSecret).getSubject()
        );
    }
}