package com.sig.sistema_gerenciador_inventario.model.dto.request;


import org.jspecify.annotations.NonNull;

import com.sig.sistema_gerenciador_inventario.model.enums.UserRole;


public record UserCreateRequest(
    @NonNull String username,
    @NonNull String password,
    @NonNull UserRole roles
) {
    
}
