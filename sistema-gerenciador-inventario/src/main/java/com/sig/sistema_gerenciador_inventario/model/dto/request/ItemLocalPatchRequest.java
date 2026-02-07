package com.sig.sistema_gerenciador_inventario.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;

public record ItemLocalPatchRequest(
    @Null @NotEmpty String sectorName,
    @Null Integer position,
    @Null @NotEmpty String shelf
) {
    
}
