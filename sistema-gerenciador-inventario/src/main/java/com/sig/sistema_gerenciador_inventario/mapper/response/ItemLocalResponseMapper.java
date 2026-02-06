package com.sig.sistema_gerenciador_inventario.mapper.response;

import com.sig.sistema_gerenciador_inventario.model.ItemLocal;
import com.sig.sistema_gerenciador_inventario.model.dto.response.ItemLocalResponse;

public interface ItemLocalResponseMapper {
    public static ItemLocalResponse itemLocalMap(ItemLocal itemLocal){
        return new ItemLocalResponse(itemLocal.getLocal_id(),itemLocal.getSectorName(),
                itemLocal.getPosition(), itemLocal.getShelf());
    }
}
