package com.sig.sistema_gerenciador_inventario.model.dto;

import com.sig.sistema_gerenciador_inventario.model.enums.UserRole;

public record UserResponse(
    String username,
    UserRole roles
) {
    
}
