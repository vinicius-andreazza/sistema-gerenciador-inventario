package com.sig.sistema_gerenciador_inventario.model.dto.request;


import com.sig.sistema_gerenciador_inventario.model.enums.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record UserRequest(
    @NotBlank String username,
    @NotBlank String password,
    @NotNull UserRole roles
) {
    
}
