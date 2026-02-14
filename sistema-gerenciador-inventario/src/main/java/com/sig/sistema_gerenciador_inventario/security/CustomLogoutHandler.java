package com.sig.sistema_gerenciador_inventario.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import com.sig.sistema_gerenciador_inventario.repository.RefreshTokenRepository;
import com.sig.sistema_gerenciador_inventario.security.jwt.JwtClaims;

import jakarta.servlet.http.Cookie;
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

        String jwt = "";
        String username = "";

        if (authHeader != null || authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            username = jwtClaims.extractSubject(jwt);

            
        }

        
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    jwt = cookie.getValue();
                    username = jwtClaims.extractSubject(jwt);
                }
            }
        }

        Cookie cookieToken = new Cookie("token", "");
        cookieToken.setHttpOnly(true);
        cookieToken.setPath("/");
        cookieToken.setAttribute("SameSite", "Lax");
        cookieToken.setMaxAge(0);

        Cookie cookieRefreshToken = new Cookie("refreshToken", "");
        cookieRefreshToken.setHttpOnly(true);
        cookieRefreshToken.setPath("/");
        cookieToken.setAttribute("SameSite", "Lax");
        cookieRefreshToken.setMaxAge(0);


        response.addHeader("Set-Cookie", cookieToken.toString());
        response.addHeader("Set-Cookie", cookieRefreshToken.toString());

        refreshTokenRepository.findByUsername(username)
                .ifPresent(refreshTokenRepository::delete);
    }
}