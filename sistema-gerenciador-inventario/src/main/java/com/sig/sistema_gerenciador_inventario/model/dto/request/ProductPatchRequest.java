package com.sig.sistema_gerenciador_inventario.model.dto.request;

import jakarta.annotation.Nullable;
import lombok.Getter;

@Getter
public class ProductPatchRequest extends ItemPatchRequest {
    @Nullable
    private Double value;

    @Nullable
    private Double weight;

    @Nullable
    private Double height;

    @Nullable
    private Double length;

    @Nullable
    private Double depth;
}
