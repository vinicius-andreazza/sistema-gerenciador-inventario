package com.sig.sistema_gerenciador_inventario.security.jwt;

import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtEncoder {

    private final JwtProperties jwtProperties;
    private final int EXPIRATION_TIME = 3600;

    public String generateToken(String username, Map<String, String> claims) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .claims(claims)
                .signWith(jwtProperties.getSecretKey())
                .compact();
    }
}
