package com.sig.sistema_gerenciador_inventario.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sig.sistema_gerenciador_inventario.model.dto.UserLoginRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.UserLoginResponse;
import com.sig.sistema_gerenciador_inventario.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class AuthController {
    private final AuthService authService;
    
    @PostMapping("/")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest){
        return authService.login(userLoginRequest);
    }
}
