package com.sig.sistema_gerenciador_inventario.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemLocalRequest(
    @NotBlank String sectorName,
    @NotNull Integer position,
    @NotBlank String shelf
) {
    
}
