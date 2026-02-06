package com.sig.sistema_gerenciador_inventario.model.dto.request;

import java.util.Set;

import org.jspecify.annotations.NonNull;

import com.sig.sistema_gerenciador_inventario.model.Supplier;

import lombok.Getter;

@Getter
public class RawMaterialRequest extends ItemRequest {
    @NonNull
    private int batch;

    @NonNull
    private double unitValue;

    @NonNull
    private Set<Supplier> supplier;
}
