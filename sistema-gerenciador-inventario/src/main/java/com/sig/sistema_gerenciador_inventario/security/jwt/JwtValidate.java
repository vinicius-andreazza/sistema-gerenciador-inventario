package com.sig.sistema_gerenciador_inventario.security.jwt;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtValidate {

    private final JwtClaims jwtClaims;

    public boolean validateAccessToken(String token, UserDetails userDetails) {
        try {
            return userDetails.getUsername().equals(jwtClaims.extractSubject(token))
                    && "access".equals(jwtClaims.extractType(token))
                    && !jwtClaims.extractExpiration(token).before(new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            return false;
        }
    }
    public boolean validateRefreshToken(String token) {
        try {
            return  "refresh".equals(jwtClaims.extractType(token))
                    && !jwtClaims.extractExpiration(token).before(new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            return false;
        }
    }
}
