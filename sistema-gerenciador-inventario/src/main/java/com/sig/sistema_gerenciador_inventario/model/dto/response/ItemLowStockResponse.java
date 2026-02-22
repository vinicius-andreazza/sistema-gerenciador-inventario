package com.sig.sistema_gerenciador_inventario.model.dto.response;

public record ItemLowStockResponse(
    Long id,
    String name,
    Integer quantity,
    Integer minimiumQuantity,
    Double value
) {
    
}
