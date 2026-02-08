package com.sig.sistema_gerenciador_inventario.mapper.response;

import com.sig.sistema_gerenciador_inventario.model.Product;
import com.sig.sistema_gerenciador_inventario.model.dto.response.ProductResponse;

public interface ProductResponseMapper {
    public static ProductResponse productMap(Product product){
        return new ProductResponse(
                product.getItem_id(),
                product.getName(),
                product.getCategory(),
                product.getDescription(),
                product.getQuantity(),
                product.getMinimiumQuantity(),
                product.getMeasure(),
                product.getStatus(),
                product.getItemLocal(),
                product.getValue(),
                product.getWeight(),
                product.getHeight(),
                product.getLength(),
                product.getDepth()
        );
    }
}
