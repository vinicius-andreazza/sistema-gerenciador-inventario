package com.sig.sistema_gerenciador_inventario.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sig.sistema_gerenciador_inventario.exception.ExpiredRefreshTokenException;
import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.model.dto.request.RefreshTokenRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.UserLoginRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.RefreshTokenResponse;
import com.sig.sistema_gerenciador_inventario.model.dto.response.UserLoginResponse;
import com.sig.sistema_gerenciador_inventario.repository.UserRepository;
import com.sig.sistema_gerenciador_inventario.security.jwt.JwtClaims;
import com.sig.sistema_gerenciador_inventario.security.jwt.JwtEncoder;
import com.sig.sistema_gerenciador_inventario.security.jwt.JwtValidate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final JwtValidate jwtValidate;
    private final JwtClaims jwtClaims;
    private final UserRepository userRepository;

    public UserLoginResponse login(UserLoginRequest login){
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(login.username(), login.password())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        String token = jwtEncoder.generateToken(auth.getName(), auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());

        String refreshToken = jwtEncoder.generateRefreshToken(auth.getName());
        return new UserLoginResponse(token, refreshToken);
    }

    public RefreshTokenResponse refreshToken(RefreshTokenRequest refresh){
        if(!jwtValidate.validateRefreshToken(refresh.refreshToken())){
            throw new ExpiredRefreshTokenException("Resfresh Token expirado");
        }
        User user = userRepository.findByUsername(jwtClaims.extractSubject(refresh.refreshToken()));;

        String token = jwtEncoder.generateToken(user.getUsername(), List.of(String.valueOf(new SimpleGrantedAuthority(user.getRoles().name()))));

        return new RefreshTokenResponse(token, refresh.refreshToken());
    }
}
