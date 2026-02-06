package com.sig.sistema_gerenciador_inventario.mapper.models;

import com.sig.sistema_gerenciador_inventario.model.RawMaterial;
import com.sig.sistema_gerenciador_inventario.model.dto.request.RawMaterialRequest;

public interface RawMaterialMapper {
    public static RawMaterial rawMaterialMap(RawMaterialRequest request) {
        return new RawMaterial(
                request.getName(),
                request.getCategory(),
                request.getTypeItem(),
                request.getDescription(),
                request.getQuantity(),
                request.getMinimiumQuantity(),
                request.getMeasure(),
                request.getStatus(),
                request.getUser(),
                request.getItemLocal(),
                request.getBatch(),
                request.getUnitValue(),
                request.getSupplier()
        );
    }
}
