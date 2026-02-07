package com.sig.sistema_gerenciador_inventario.mapper.models;

import com.sig.sistema_gerenciador_inventario.model.Product;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ProductPatchRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ProductRequest;

public interface ProductMapper {
    public static Product productMap(ProductRequest request){
        return new Product(
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
                request.getValue(),
                request.getWeight(),
                request.getHeight(),
                request.getLength(),
                request.getDepth()
        );
    }
}
