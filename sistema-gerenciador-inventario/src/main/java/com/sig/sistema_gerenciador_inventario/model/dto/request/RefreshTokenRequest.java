package com.sig.sistema_gerenciador_inventario.model.dto.request;

import org.jspecify.annotations.NonNull;

public record RefreshTokenRequest(
    @NonNull String token,
    @NonNull String refreshToken
) {
    
}
