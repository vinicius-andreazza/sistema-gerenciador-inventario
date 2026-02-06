package com.sig.sistema_gerenciador_inventario.mapper.models;

import com.sig.sistema_gerenciador_inventario.model.Supplier;
import com.sig.sistema_gerenciador_inventario.model.dto.request.SupplierRequest;

public interface SupplierMapper {
    public static Supplier supplierMap(SupplierRequest request){
        return new Supplier(request.name(), request.phone(),
                request.email(), request.rawMaterials());
    }
}
