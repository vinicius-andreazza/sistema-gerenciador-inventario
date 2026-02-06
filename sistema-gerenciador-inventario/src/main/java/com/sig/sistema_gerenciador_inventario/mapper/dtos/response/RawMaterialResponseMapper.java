package com.sig.sistema_gerenciador_inventario.mapper.dtos.response;

import com.sig.sistema_gerenciador_inventario.model.RawMaterial;
import com.sig.sistema_gerenciador_inventario.model.dto.response.RawMaterialResponse;

public interface RawMaterialResponseMapper {
    public static RawMaterialResponse rawMaterialMapper(RawMaterial rawMaterial){
        return new RawMaterialResponse(
                rawMaterial.getName(),
                rawMaterial.getCategory(),
                rawMaterial.getDescription(),
                rawMaterial.getQuantity(),
                rawMaterial.getMinimiumQuantity(),
                rawMaterial.getMeasure(),
                rawMaterial.getStatus(),
                rawMaterial.getItemLocal(),
                rawMaterial.getBatch(),
                rawMaterial.getUnitValue(),
                rawMaterial.getSupplier()
        );
    }
}
