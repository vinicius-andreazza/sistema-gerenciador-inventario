package com.sig.sistema_gerenciador_inventario.model.dto.request;

import java.util.Set;

import com.sig.sistema_gerenciador_inventario.model.RawMaterial;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record SupplierRequest(
    @NotNull @NotBlank String name,
    @NotEmpty @Pattern(regexp = "^\\(\\d{2}\\)\\s9\\d{4}-\\d{4}$", message = "Celular inválido") String phone,
    @NotNull @NotBlank @Email String email,
    Set<RawMaterial> rawMaterials
) {
    
}
