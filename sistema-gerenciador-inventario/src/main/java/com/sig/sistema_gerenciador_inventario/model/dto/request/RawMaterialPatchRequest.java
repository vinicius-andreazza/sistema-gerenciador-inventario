package com.sig.sistema_gerenciador_inventario.model.dto.request;

import java.util.Set;

import com.sig.sistema_gerenciador_inventario.model.Supplier;

import jakarta.validation.constraints.Null;
import lombok.Getter;

@Getter
public class RawMaterialPatchRequest extends ItemPatchRequest {
    @Null
    private Integer batch;

    @Null
    private Double unitValue;

    @Null
    private Set<Supplier> supplier;
}
