package com.sig.sistema_gerenciador_inventario.model.dto.request;

import com.sig.sistema_gerenciador_inventario.model.enums.UserRole;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;

public record UserPatchRequest(
    @Null @NotEmpty String username,
    @Null @NotEmpty String password,
    @Null UserRole roles) {
}