package com.sig.sistema_gerenciador_inventario.service;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sig.sistema_gerenciador_inventario.model.dto.UserLoginRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.UserLoginResponse;
import com.sig.sistema_gerenciador_inventario.security.jwt.JwtEncoder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;

    public ResponseEntity<UserLoginResponse> login(UserLoginRequest login){
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(login.username(), login.password())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwtEncoder.generateToken(auth.getName(), auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
        return new ResponseEntity<UserLoginResponse>(new UserLoginResponse(token), HttpStatusCode.valueOf(200));
    }
}
