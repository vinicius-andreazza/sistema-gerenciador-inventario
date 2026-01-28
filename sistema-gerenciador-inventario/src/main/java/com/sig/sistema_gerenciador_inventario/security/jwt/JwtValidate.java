package com.sig.sistema_gerenciador_inventario.security.jwt;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtValidate {

    private final JwtClaims jwtClaims;

    public boolean validate(String token, UserDetails userDetails){
        return (userDetails.getUsername().equals(jwtClaims.extractSubject(token)) && jwtClaims.extractExpiration(token).before(new Date(System.currentTimeMillis())));
    }
}
