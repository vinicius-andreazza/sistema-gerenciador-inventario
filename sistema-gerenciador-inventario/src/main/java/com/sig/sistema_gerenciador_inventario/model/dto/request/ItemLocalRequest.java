package com.sig.sistema_gerenciador_inventario.model.dto.request;

import org.jspecify.annotations.NonNull;

public record ItemLocalRequest(
    @NonNull String sectorName,
    @NonNull Integer position,
    @NonNull String shelf
) {
    
}
