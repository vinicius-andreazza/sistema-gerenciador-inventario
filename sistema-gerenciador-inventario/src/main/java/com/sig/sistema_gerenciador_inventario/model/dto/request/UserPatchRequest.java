package com.sig.sistema_gerenciador_inventario.model.dto.request;

import com.sig.sistema_gerenciador_inventario.model.enums.UserRole;

import jakarta.annotation.Nullable;

public record UserPatchRequest(
    @Nullable String username,
    @Nullable String password,
    @Nullable UserRole roles) {
}