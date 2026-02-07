package com.sig.sistema_gerenciador_inventario.model.dto.request;

import jakarta.validation.constraints.NotNull;

public record RefreshTokenRequest(
    @NotNull String token,
    @NotNull String refreshToken
) {
    
}
