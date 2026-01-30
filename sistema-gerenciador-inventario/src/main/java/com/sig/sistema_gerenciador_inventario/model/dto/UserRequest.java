package com.sig.sistema_gerenciador_inventario.model.dto;


import org.jspecify.annotations.NonNull;

import com.sig.sistema_gerenciador_inventario.model.enums.UserRole;


public record UserRequest(
    @NonNull String username,
    @NonNull String password,
    @NonNull UserRole roles
) {
    
}
