package com.sig.sistema_gerenciador_inventario.model.dto.request;

import org.jspecify.annotations.NonNull;

public record UserLoginRequest(
    @NonNull String username,
    @NonNull String password
) {
}
