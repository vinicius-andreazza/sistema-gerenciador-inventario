package com.sig.sistema_gerenciador_inventario.mapper.models;

import com.sig.sistema_gerenciador_inventario.model.ItemLocal;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ItemLocalRequest;

public interface ItemLocalMapper {
    public static ItemLocal itemLocalMap(ItemLocalRequest request){
        return new ItemLocal(request.sectorName(), request.position(),
                request.shelf());
    }
}
