package com.sig.sistema_gerenciador_inventario.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtEncoder {

    private final JwtProperties jwtProperties;
    private final int EXPIRATION_TIME = 60000;

    public String generateToken(String username, List<String> role) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .claim("role", role)
                .claim("type", "access")
                .signWith(Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .claim("type", "refresh")
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME * 60))
                .signWith(Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8)))
                .compact();
    }
}
