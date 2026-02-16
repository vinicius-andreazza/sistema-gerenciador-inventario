package com.sig.sistema_gerenciador_inventario.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sig.sistema_gerenciador_inventario.mapper.models.ProductMapper;
import com.sig.sistema_gerenciador_inventario.mapper.response.ProductResponseMapper;
import com.sig.sistema_gerenciador_inventario.model.Product;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ProductPatchRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ProductRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.ProductResponse;
import com.sig.sistema_gerenciador_inventario.repository.ItemRepository;
import com.sig.sistema_gerenciador_inventario.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import com.sig.sistema_gerenciador_inventario.model.Item;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;
    private final ItemService itemService;

    public ProductResponse create(ProductRequest productRequest) {
        Item product = new Product();
        product = ProductMapper.productMap(productRequest);
        Product productCreated = (Product) itemRepository.save(product);
        return ProductResponseMapper.productMap(productCreated);
    }

    public Page<ProductResponse> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductResponseMapper::productMap);
    }

    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id inválido"));
        return ProductResponseMapper.productMap(product);
    }

    @Transactional
    public ProductResponse putUpdate(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id inválido"));
        product = ProductMapper.productMap(productRequest);
        product.setItem_id(id);
        Product productUpdated = (Product) itemRepository.save(product);
        return ProductResponseMapper.productMap(productUpdated);
    }

    @Transactional
    public ProductResponse patchUpdate(Long id, ProductPatchRequest productRequest) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id inválido"));
        patchProductFields(product, productRequest);
        Product productUpdated = (Product) itemRepository.save(product);
        return ProductResponseMapper.productMap(productUpdated);
    }

    @Transactional
    public void deleteById(Long id){
        try {
            itemRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void patchProductFields(
            Product product,
            ProductPatchRequest request) {
        itemService.patchItemFields(product, request);

        product.setValue(
                request.getValue() != null ? request.getValue() : product.getValue());

        product.setWeight(
                request.getWeight() != null ? request.getWeight() : product.getWeight());

        product.setHeight(
                request.getHeight() != null ? request.getHeight() : product.getHeight());

        product.setLength(
                request.getLength() != null ? request.getLength() : product.getLength());

        product.setDepth(
                request.getDepth() != null ? request.getDepth() : product.getDepth());
    }

}
