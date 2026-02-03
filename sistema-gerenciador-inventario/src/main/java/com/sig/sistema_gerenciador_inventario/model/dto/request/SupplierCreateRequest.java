package com.sig.sistema_gerenciador_inventario.model.dto.request;

import java.util.Set;

import org.jspecify.annotations.NonNull;

import com.sig.sistema_gerenciador_inventario.model.RawMaterial;

import jakarta.annotation.Nullable;

public record SupplierCreateRequest(
    @NonNull String name,
    @Nullable String phone,
    @NonNull String email,
    @Nullable Set<RawMaterial> rawMaterials
) {
    
}
