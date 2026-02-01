package com.sig.sistema_gerenciador_inventario.model.dto.request;

import org.jspecify.annotations.NonNull;

import com.sig.sistema_gerenciador_inventario.model.enums.UserRole;

import jakarta.annotation.Nullable;

public record UserUpdateRequest(
    @NonNull Long id,
    @Nullable String username,
    @Nullable String password,
    @Nullable UserRole roles) {
}