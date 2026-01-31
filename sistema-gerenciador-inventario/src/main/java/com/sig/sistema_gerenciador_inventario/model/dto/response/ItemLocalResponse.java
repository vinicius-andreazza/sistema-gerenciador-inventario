package com.sig.sistema_gerenciador_inventario.model.dto.response;

import org.jspecify.annotations.NonNull;

public record ItemLocalResponse(
    @NonNull String sectorName,
    @NonNull int position,
    @NonNull String shelf
) { 
}
