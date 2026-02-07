package com.sig.sistema_gerenciador_inventario.model.dto.request;

import java.util.Set;

import com.sig.sistema_gerenciador_inventario.model.Supplier;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class RawMaterialRequest extends ItemRequest {
    @NotNull
    private Integer batch;

    @NotNull
    private Double unitValue;

    @NotNull
    private Set<Supplier> supplier;
}
