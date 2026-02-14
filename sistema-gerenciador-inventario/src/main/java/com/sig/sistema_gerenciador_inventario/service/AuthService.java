package com.sig.sistema_gerenciador_inventario.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sig.sistema_gerenciador_inventario.model.RefreshToken;
import com.sig.sistema_gerenciador_inventario.model.dto.request.UserLoginRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.UserLoginResponse;
import com.sig.sistema_gerenciador_inventario.repository.RefreshTokenRepository;
import com.sig.sistema_gerenciador_inventario.security.jwt.JwtEncoder;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    public UserLoginResponse login(UserLoginRequest login, HttpServletResponse response){
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(login.username(), login.password())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        
        String token = jwtEncoder.generateToken(auth.getName(), auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
        
        RefreshToken refreshToken =  new RefreshToken(jwtEncoder.generateRefreshToken(auth.getName()), auth.getName());
        refreshTokenRepository.save(refreshToken);


        Cookie cookieToken = new Cookie("token", token);
        cookieToken.setHttpOnly(true);
        cookieToken.setPath("/");
        cookieToken.setAttribute("SameSite", "Lax");
        cookieToken.setMaxAge(60 * 5);

        Cookie cookieRefreshToken = new Cookie("refreshToken", refreshToken.getToken());
        cookieRefreshToken.setHttpOnly(true);
        cookieRefreshToken.setPath("/");
        cookieToken.setAttribute("SameSite", "Lax");
        cookieRefreshToken.setMaxAge(60 * 60 * 24 * 7);

        response.addCookie(cookieToken);
        response.addCookie(cookieRefreshToken);
        return new UserLoginResponse(token, refreshToken.getToken());
    }
    
}
