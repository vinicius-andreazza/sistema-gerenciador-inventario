package com.sig.sistema_gerenciador_inventario.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
public class JwtProperties {
    @Value("${jwt.secret}")
    private String secretKey;
}
