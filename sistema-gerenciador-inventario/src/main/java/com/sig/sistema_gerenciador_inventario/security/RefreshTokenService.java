package com.sig.sistema_gerenciador_inventario.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.sig.sistema_gerenciador_inventario.security.jwt.JwtClaims;
import com.sig.sistema_gerenciador_inventario.security.jwt.JwtEncoder;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final JwtEncoder jwtEncoder;
    private final JwtClaims jwtClaims;
    private final CustomUserDetailsService userDetailsService;

    public void refreshToken(HttpServletRequest request, HttpServletResponse response, String refresh) {
        UserDetails user = userDetailsService.loadUserByUsername(jwtClaims.extractSubject(refresh));

        String token = jwtEncoder.generateToken(user.getUsername(), user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).map(String::valueOf)
                .toList());

        Cookie cookieToken = new Cookie("token", token);
        cookieToken.setHttpOnly(true);
        cookieToken.setPath("/");
        cookieToken.setAttribute("SameSite", "Lax");
        cookieToken.setMaxAge(60 * 5);

        response.addCookie(cookieToken);
    }
}
