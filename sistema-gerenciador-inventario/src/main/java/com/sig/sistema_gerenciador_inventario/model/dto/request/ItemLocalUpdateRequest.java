package com.sig.sistema_gerenciador_inventario.model.dto.request;


import org.jspecify.annotations.NonNull;

import jakarta.annotation.Nullable;

public record ItemLocalUpdateRequest(
    @NonNull Long id,
    @Nullable String sectorName,
    @Nullable Integer position,
    @Nullable String shelf
) {
    
}
