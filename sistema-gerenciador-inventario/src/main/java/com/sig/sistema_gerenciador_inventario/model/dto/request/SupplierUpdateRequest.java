package com.sig.sistema_gerenciador_inventario.model.dto.request;

import java.util.Set;

import org.jspecify.annotations.NonNull;

import com.sig.sistema_gerenciador_inventario.model.RawMaterial;

import jakarta.annotation.Nullable;

public record SupplierUpdateRequest(
    @NonNull Long id,
    @Nullable String name,
    @Nullable String phone,
    @Nullable String email,
    @Nullable Set<RawMaterial> rawMaterials
) {
    
}
