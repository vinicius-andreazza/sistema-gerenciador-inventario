package com.sig.sistema_gerenciador_inventario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sig.sistema_gerenciador_inventario.mapper.models.ProductMapper;
import com.sig.sistema_gerenciador_inventario.mapper.response.ProductResponseMapper;
import com.sig.sistema_gerenciador_inventario.model.Product;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ProductRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.ProductResponse;
import com.sig.sistema_gerenciador_inventario.repository.ItemRepository;
import com.sig.sistema_gerenciador_inventario.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

import com.sig.sistema_gerenciador_inventario.model.Item;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ItemRepository itemRepository;
    private final ProductRepository productRespository;

    public ProductResponse create(ProductRequest productRequest) {
        Item product = new Product();
        product = ProductMapper.productMap(productRequest);
        Product productCreated = (Product) itemRepository.save(product);
        return ProductResponseMapper.productMap(productCreated);
    }

    public List<ProductResponse> findAll() {
        return productRespository.findAll().stream().map(ProductResponseMapper::productMap).toList();
    }

    public ProductResponse findById(Long id) {
        Product product = productRespository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id inválido"));
        return ProductResponseMapper.productMap(product);
    }
}
