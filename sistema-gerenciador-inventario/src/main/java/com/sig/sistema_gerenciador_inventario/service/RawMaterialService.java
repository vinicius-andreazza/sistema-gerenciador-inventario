package com.sig.sistema_gerenciador_inventario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sig.sistema_gerenciador_inventario.mapper.models.RawMaterialMapper;
import com.sig.sistema_gerenciador_inventario.mapper.response.RawMaterialResponseMapper;
import com.sig.sistema_gerenciador_inventario.model.RawMaterial;
import com.sig.sistema_gerenciador_inventario.model.dto.request.RawMaterialRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.RawMaterialResponse;
import com.sig.sistema_gerenciador_inventario.repository.ItemRepository;
import com.sig.sistema_gerenciador_inventario.repository.RawMaterialRepository;

import jakarta.persistence.EntityNotFoundException;

import com.sig.sistema_gerenciador_inventario.model.Item;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RawMaterialService {
    private final ItemRepository itemRepository;
    private final RawMaterialRepository rawMaterialRepository;

    public RawMaterialResponse create(RawMaterialRequest rawMaterialRequest) {
        Item rawMaterial = new RawMaterial();
        rawMaterial = RawMaterialMapper.rawMaterialMap(rawMaterialRequest);
        RawMaterial rawMaterialCreated = (RawMaterial) itemRepository.save(rawMaterial);
        return RawMaterialResponseMapper.rawMaterialMap(rawMaterialCreated);
    }

    public List<RawMaterialResponse> findAll() {
        return rawMaterialRepository.findAll().stream().map(RawMaterialResponseMapper::rawMaterialMap).toList();
    }

    public RawMaterialResponse findById(Long id) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id incorreto"));
        return RawMaterialResponseMapper.rawMaterialMap(rawMaterial);
    }
}
