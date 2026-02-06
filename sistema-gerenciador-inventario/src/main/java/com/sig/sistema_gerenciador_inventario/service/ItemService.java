package com.sig.sistema_gerenciador_inventario.service;

import org.springframework.stereotype.Service;

import com.sig.sistema_gerenciador_inventario.model.Item;
import com.sig.sistema_gerenciador_inventario.model.Product;
import com.sig.sistema_gerenciador_inventario.model.RawMaterial;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ProductRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.RawMaterialRequest;
import com.sig.sistema_gerenciador_inventario.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Product create(ProductRequest productRequest){
        Item product = new Product();
        product = toProduct(productRequest);
        return (Product) itemRepository.save(product);
    }

    public RawMaterial create(RawMaterialRequest rawMaterialRequest){
        Item rawMaterial = new RawMaterial();
        rawMaterial = toRawMaterial(rawMaterialRequest);
        return (RawMaterial) itemRepository.save(rawMaterial);
    }

    private Product toProduct(ProductRequest request) {
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

    private RawMaterial toRawMaterial(RawMaterialRequest request) {

        return new RawMaterial(
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
                request.getBatch(),
                request.getUnitValue(),
                request.getSupplier()
        );
    }
}
