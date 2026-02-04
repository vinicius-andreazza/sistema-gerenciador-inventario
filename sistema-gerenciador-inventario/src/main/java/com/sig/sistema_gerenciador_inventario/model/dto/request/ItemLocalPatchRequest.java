package com.sig.sistema_gerenciador_inventario.model.dto.request;

import jakarta.annotation.Nullable;

public record ItemLocalPatchRequest(
    @Nullable String sectorName,
    @Nullable Integer position,
    @Nullable String shelf
) {
    
}
