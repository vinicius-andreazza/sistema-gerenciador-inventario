package com.sig.sistema_gerenciador_inventario.service;

import org.springframework.stereotype.Service;

import com.sig.sistema_gerenciador_inventario.mapper.dtos.response.ProductResponseMapper;
import com.sig.sistema_gerenciador_inventario.mapper.dtos.response.RawMaterialResponseMapper;
import com.sig.sistema_gerenciador_inventario.mapper.models.ProductMapper;
import com.sig.sistema_gerenciador_inventario.mapper.models.RawMaterialMapper;
import com.sig.sistema_gerenciador_inventario.model.Item;
import com.sig.sistema_gerenciador_inventario.model.Product;
import com.sig.sistema_gerenciador_inventario.model.RawMaterial;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ProductRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.RawMaterialRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.ProductResponse;
import com.sig.sistema_gerenciador_inventario.model.dto.response.RawMaterialResponse;
import com.sig.sistema_gerenciador_inventario.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public ProductResponse create(ProductRequest productRequest){
        Item product = new Product();
        product = ProductMapper.productMapper(productRequest);
        Product productCreated = (Product) itemRepository.save(product);
        return ProductResponseMapper.productMapper(productCreated);
    }

    public RawMaterialResponse create(RawMaterialRequest rawMaterialRequest){
        Item rawMaterial = new RawMaterial();
        rawMaterial = RawMaterialMapper.rawMaterialMapper(rawMaterialRequest);
        RawMaterial rawMaterialCreated = (RawMaterial) itemRepository.save(rawMaterial);
        return RawMaterialResponseMapper.rawMaterialMapper(rawMaterialCreated);
    }
                    
}
