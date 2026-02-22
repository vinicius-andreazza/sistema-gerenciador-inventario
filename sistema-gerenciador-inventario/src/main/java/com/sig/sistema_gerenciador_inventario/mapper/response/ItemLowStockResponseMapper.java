package com.sig.sistema_gerenciador_inventario.mapper.response;

import com.sig.sistema_gerenciador_inventario.model.Item;
import com.sig.sistema_gerenciador_inventario.model.dto.response.ItemLowStockResponse;

public interface ItemLowStockResponseMapper {
    public static ItemLowStockResponse itemLowStockMapper(Item item){
        return new ItemLowStockResponse(item.getItem_id(), item.getName(), item.getQuantity(), item.getMinimiumQuantity(), item.getValue());
    }
}
