package com.sig.sistema_gerenciador_inventario.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sig.sistema_gerenciador_inventario.model.dto.request.RefreshTokenRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.UserLoginRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.RefreshTokenResponse;
import com.sig.sistema_gerenciador_inventario.model.dto.response.UserLoginResponse;
import com.sig.sistema_gerenciador_inventario.service.AuthService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@RequestMapping()
public class AuthController {
    private final AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest, HttpServletResponse response){
        return ResponseEntity.ok(authService.login(userLoginRequest, response));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }
    
}
