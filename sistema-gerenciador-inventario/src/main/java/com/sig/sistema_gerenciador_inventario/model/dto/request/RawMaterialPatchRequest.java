package com.sig.sistema_gerenciador_inventario.model.dto.request;

import java.util.Set;

import com.sig.sistema_gerenciador_inventario.model.Supplier;

import jakarta.annotation.Nullable;
import lombok.Getter;

@Getter
public class RawMaterialPatchRequest extends ItemPatchRequest {
    @Nullable
    private Integer batch;

    @Nullable
    private Double unitValue;

    @Nullable
    private Set<Supplier> supplier;
}
