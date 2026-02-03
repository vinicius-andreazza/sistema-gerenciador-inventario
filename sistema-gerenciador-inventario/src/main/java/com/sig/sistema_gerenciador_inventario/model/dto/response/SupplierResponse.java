package com.sig.sistema_gerenciador_inventario.model.dto.response;

import org.jspecify.annotations.NonNull;

import jakarta.annotation.Nullable;

public record SupplierResponse(
    @NonNull Long id,
    @NonNull String name,
    @Nullable String phone,
    @NonNull String email
) {
    
}
