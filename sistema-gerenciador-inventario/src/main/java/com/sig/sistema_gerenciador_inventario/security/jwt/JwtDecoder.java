package com.sig.sistema_gerenciador_inventario.security.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtDecoder {
    private final JwtProperties jwtProperties;
    public JwtParser decoder(String token){
        return Jwts.parser().verifyWith(jwtProperties.getSecretKey()).build();
    }
}
