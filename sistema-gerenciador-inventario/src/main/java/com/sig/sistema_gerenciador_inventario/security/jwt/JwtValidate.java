package com.sig.sistema_gerenciador_inventario.security.jwt;

import java.util.Date;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.sig.sistema_gerenciador_inventario.model.RefreshToken;
import com.sig.sistema_gerenciador_inventario.repository.RefreshTokenRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtValidate {

    private final RefreshTokenRepository refreshTokenRepository;
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
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken(token);
        if(refreshToken.isPresent()){
            return false;
        }
        try {
            return  "refresh".equals(jwtClaims.extractType(refreshToken.get().getToken()))
                    && !jwtClaims.extractExpiration(refreshToken.get().getToken()).before(new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            return false;
        }
    }
}
