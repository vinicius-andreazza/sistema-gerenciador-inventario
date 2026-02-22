package com.sig.sistema_gerenciador_inventario.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sig.sistema_gerenciador_inventario.mapper.models.RawMaterialMapper;
import com.sig.sistema_gerenciador_inventario.mapper.response.RawMaterialResponseMapper;
import com.sig.sistema_gerenciador_inventario.model.RawMaterial;
import com.sig.sistema_gerenciador_inventario.model.dto.request.RawMaterialPatchRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.RawMaterialRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.RawMaterialResponse;
import com.sig.sistema_gerenciador_inventario.repository.ItemLocalRepository;
import com.sig.sistema_gerenciador_inventario.repository.ItemRepository;
import com.sig.sistema_gerenciador_inventario.repository.RawMaterialRepository;
import com.sig.sistema_gerenciador_inventario.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import com.sig.sistema_gerenciador_inventario.model.Item;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RawMaterialService {
    private final ItemRepository itemRepository;
    private final RawMaterialRepository rawMaterialRepository;
    private final ItemService itemService;
    private final UserRepository userRepository;
    private final ItemLocalRepository itemLocalRepository;

    public RawMaterialResponse create(RawMaterialRequest rawMaterialRequest) {
        Item rawMaterial = new RawMaterial();
        rawMaterial = RawMaterialMapper.rawMaterialMap(rawMaterialRequest);
        rawMaterial.setUser(userRepository.findById(rawMaterialRequest.getUserId()).orElseThrow(() -> new EntityNotFoundException()));
        rawMaterial.setItemLocal(itemLocalRepository.findById(rawMaterialRequest.getItemLocalId()).orElseThrow(() -> new EntityNotFoundException()));
        RawMaterial rawMaterialCreated = (RawMaterial) itemRepository.save(rawMaterial);
        return RawMaterialResponseMapper.rawMaterialMap(rawMaterialCreated);
    }

    public Page<RawMaterialResponse> findAll(Pageable pageable) {
        return rawMaterialRepository.findAll(pageable).map(RawMaterialResponseMapper::rawMaterialMap);
    }

    public RawMaterialResponse findById(Long id) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id incorreto"));
        return RawMaterialResponseMapper.rawMaterialMap(rawMaterial);
    }

    @Transactional
    public RawMaterialResponse putUpdate(Long id, RawMaterialRequest rawMaterialRequest) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id inválido"));
        rawMaterial = RawMaterialMapper.rawMaterialMap(rawMaterialRequest);
        rawMaterial.setItem_id(id);
        RawMaterial rawMaterialUpdated = (RawMaterial) itemRepository.save(rawMaterial);
        return RawMaterialResponseMapper.rawMaterialMap(rawMaterialUpdated);
    }

    @Transactional
    public RawMaterialResponse patchUpdate(Long id, RawMaterialPatchRequest rawMaterialRequest) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id inválido"));
        patchRawMaterialFields(rawMaterial, rawMaterialRequest);
        RawMaterial rawMaterialUpdated = (RawMaterial) itemRepository.save(rawMaterial);
        return RawMaterialResponseMapper.rawMaterialMap(rawMaterialUpdated);
    }

    @Transactional
    public void deleteById(Long id){
        try {
            itemRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void patchRawMaterialFields(
            RawMaterial rawMaterial,
            RawMaterialPatchRequest request) {
        itemService.patchItemFields(rawMaterial, request);

        rawMaterial.setBatch(
                request.getBatch() != null ? request.getBatch() : rawMaterial.getBatch());

        rawMaterial.setValue(
                request.getUnitValue() != null ? request.getUnitValue() : rawMaterial.getValue());
                
        rawMaterial.setSupplier(
                request.getSupplier() != null ? request.getSupplier() : rawMaterial.getSupplier());
    }
}
