package com.sig.sistema_gerenciador_inventario.model.dto.request;

import jakarta.validation.constraints.NotNull;

public record UserLoginRequest(
    @NotNull String username,
    @NotNull String password
) {
}
