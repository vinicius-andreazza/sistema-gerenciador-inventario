package com.sig.sistema_gerenciador_inventario.model.dto.response;

import java.util.Set;

import org.jspecify.annotations.NonNull;

import com.sig.sistema_gerenciador_inventario.model.Supplier;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ItemRequest;

import lombok.Getter;

@Getter
public class RawMaterialResponse extends ItemResponse {
    @NonNull
    private int batch;

    @NonNull
    private double unitValue;

    @NonNull
    private Set<Supplier> supplier;
}
