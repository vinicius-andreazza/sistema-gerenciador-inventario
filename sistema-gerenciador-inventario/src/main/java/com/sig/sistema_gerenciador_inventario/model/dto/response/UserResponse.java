package com.sig.sistema_gerenciador_inventario.model.dto.response;

import org.jspecify.annotations.NonNull;

import com.sig.sistema_gerenciador_inventario.model.enums.UserRole;

public record UserResponse(
    @NonNull Long id,
    @NonNull String username,
    @NonNull UserRole roles
) {
    
}
