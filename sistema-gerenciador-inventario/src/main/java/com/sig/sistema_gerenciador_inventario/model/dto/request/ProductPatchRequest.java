package com.sig.sistema_gerenciador_inventario.model.dto.request;

import jakarta.validation.constraints.Null;
import lombok.Getter;

@Getter
public class ProductPatchRequest extends ItemPatchRequest {
    @Null
    private Double value;

    @Null
    private Double weight;

    @Null
    private Double height;

    @Null
    private Double length;

    @Null
    private Double depth;
}
