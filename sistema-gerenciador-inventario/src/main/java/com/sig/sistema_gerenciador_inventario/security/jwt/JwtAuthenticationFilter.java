package com.sig.sistema_gerenciador_inventario.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sig.sistema_gerenciador_inventario.security.CustomUserDetailsService;
import com.sig.sistema_gerenciador_inventario.security.RefreshTokenService;
import com.sig.sistema_gerenciador_inventario.security.exception.JwtAuthenticationEntryPoint;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtValidate jwtValidate;
    private final JwtClaims jwtClaims;
    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final RefreshTokenService refreshTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            final String authHeader = request.getHeader("Authorization");
            String username = null;
            String jwt = null;

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
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

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (validateToken(jwt, userDetails)) {
                    SecurityContextHolder.getContext().setAuthentication(createAuthToken(userDetails, request));
                }
            }

        } catch (ExpiredJwtException ex) {

            boolean refreshed = false;

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("refreshToken".equals(cookie.getName())) {
                        System.out.println("teste");
                        if (!jwtValidate.validateRefreshToken(cookie.getValue())) {
                            System.out.println("nao valido");
                            break;
                        }
                        System.out.println("valido");
                        refreshTokenService.refreshToken(request, response, cookie.getValue());
                        refreshed = true;
                    }
                }
            }

            if (!refreshed) {
                SecurityContextHolder.clearContext();

                jwtAuthenticationEntryPoint.commence(
                        request,
                        response,
                        new InsufficientAuthenticationException("Token expirado", ex));

                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/refreshToken") || path.startsWith("/token") || path.startsWith("/login")
                || path.startsWith("/css") || path.startsWith("/js") || path.equals("/");
    }

    private Boolean validateToken(String token, UserDetails userDetails) {
        return jwtValidate.validateAccessToken(token, userDetails);
    }

    private UsernamePasswordAuthenticationToken createAuthToken(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authToken;
    }

}
