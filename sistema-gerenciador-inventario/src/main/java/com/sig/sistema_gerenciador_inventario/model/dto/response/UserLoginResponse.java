package com.sig.sistema_gerenciador_inventario.model.dto.response;

import org.jspecify.annotations.NonNull;

public record UserLoginResponse(
    @NonNull String token,
    @NonNull String refreshToken
) {
    
}
