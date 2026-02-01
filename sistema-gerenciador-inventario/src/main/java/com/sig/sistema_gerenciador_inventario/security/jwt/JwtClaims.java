package com.sig.sistema_gerenciador_inventario.security.jwt;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtClaims {
    private final JwtDecoder jwtDecoder;

    private Claims getPayload(String token){
        return jwtDecoder.decoder(token).parseSignedClaims(token).getPayload();
    }

    public List<String> extractRole(String token) {
        return getPayload(token).get("role", List.class);
    }

    public String extractSubject(String token) {
        return getPayload(token).getSubject();
    }

    public Date extractExpiration(String token) {
        return getPayload(token).getExpiration();
    }

    public String extractType(String token){
        return getPayload(token).get("type", String.class);
    }
}
