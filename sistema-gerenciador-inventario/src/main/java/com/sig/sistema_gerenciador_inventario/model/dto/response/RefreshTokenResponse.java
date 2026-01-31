package com.sig.sistema_gerenciador_inventario.model.dto.response;

public record RefreshTokenResponse(
    String token,
    String refreshToken
) {
    
}