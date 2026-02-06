package com.sig.sistema_gerenciador_inventario.mapper.response;

import com.sig.sistema_gerenciador_inventario.model.Supplier;
import com.sig.sistema_gerenciador_inventario.model.dto.response.SupplierResponse;

public interface SupplierResponseMapper {
    public static SupplierResponse supplierResponseMap(Supplier supplier){
        return new SupplierResponse(supplier.getSupplier_id(), supplier.getName(),
                supplier.getPhone(), supplier.getEmail());
    }
}
