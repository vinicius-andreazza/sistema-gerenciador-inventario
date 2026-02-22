package com.sig.sistema_gerenciador_inventario.mapper.response;

import java.util.stream.Collectors;

import com.sig.sistema_gerenciador_inventario.model.RawMaterial;
import com.sig.sistema_gerenciador_inventario.model.dto.response.RawMaterialResponse;

public interface RawMaterialResponseMapper {
    public static RawMaterialResponse rawMaterialMap(RawMaterial rawMaterial){
        return new RawMaterialResponse(
            rawMaterial.getItem_id(),
                rawMaterial.getName(),
                rawMaterial.getCategory(),
                rawMaterial.getDescription(),
                rawMaterial.getQuantity(),
                rawMaterial.getMinimiumQuantity(),
                rawMaterial.getMeasure(),
                rawMaterial.getStatus(),
                rawMaterial.getItemLocal(),
                rawMaterial.getBatch(),
                rawMaterial.getValue(),
                rawMaterial.getSupplier().stream().map(s -> s.getName()).collect(Collectors.toSet())
        );
    }
}
