package com.sig.sistema_gerenciador_inventario.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import com.sig.sistema_gerenciador_inventario.repository.RefreshTokenRepository;
import com.sig.sistema_gerenciador_inventario.security.jwt.JwtClaims;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    private final JwtClaims jwtClaims;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        String jwt = authHeader.substring(7);
        String username = jwtClaims.extractSubject(jwt);

        refreshTokenRepository.findByUsername(username)
                .ifPresent(refreshTokenRepository::delete);
    }
}