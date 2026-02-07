package com.sig.sistema_gerenciador_inventario.model.dto.request;

import java.util.Set;

import com.sig.sistema_gerenciador_inventario.model.RawMaterial;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;

public record SupplierPatchRequest(
        @Null @NotEmpty String name,
        @Null @NotEmpty @Pattern(regexp = "(?:([1-9]{2})?)(\\d{4,5})(\\d{4})$/", message = "Celular inválido") String phone,
        @Null @NotEmpty @Email String email,
        @Null Set<RawMaterial> rawMaterials) {

}
