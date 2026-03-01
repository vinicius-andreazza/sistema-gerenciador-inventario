package com.sig.sistema_gerenciador_inventario.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sig.sistema_gerenciador_inventario.mapper.models.ItemLocalMapper;
import com.sig.sistema_gerenciador_inventario.mapper.response.ItemLocalResponseMapper;
import com.sig.sistema_gerenciador_inventario.model.ItemLocal;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ItemLocalRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ItemLocalPatchRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.ItemLocalResponse;
import com.sig.sistema_gerenciador_inventario.repository.ItemLocalRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemLocalService {
    private final ItemLocalRepository itemLocalRepository;

    public ItemLocalResponse create(ItemLocalRequest itemLocalRequest) {
        ItemLocal itemLocalCreated = ItemLocalMapper.itemLocalMap(itemLocalRequest);
        itemLocalCreated = itemLocalRepository.save(itemLocalCreated);
        ItemLocalResponse itemLocalResponse = ItemLocalResponseMapper.itemLocalMap(itemLocalCreated);
        return itemLocalResponse;
    }

    public ItemLocalResponse findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id não pode ser nulo ou menor que 1");
        }
        ItemLocal itemLocal = itemLocalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Local não encontrado"));
        ItemLocalResponse itemLocalResponse = ItemLocalResponseMapper.itemLocalMap(itemLocal);
        return itemLocalResponse;
    }

    public Page<ItemLocalResponse> findAll(String search, Pageable pageable) {
        if (search != null && !search.isEmpty()) {
            return itemLocalRepository.findBySetorOrPacote(search, pageable).map(ItemLocalResponseMapper::itemLocalMap);
        }
        return itemLocalRepository.findAll(pageable).map(ItemLocalResponseMapper::itemLocalMap);
    }

    @Transactional
    public ItemLocalResponse putUpdate(Long id, ItemLocalRequest itemLocalRequest) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id não pode ser nulo ou menor que 1");
        }

        ItemLocal itemLocalShouldBeUpdated = itemLocalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Local não encontrado"));

        itemLocalShouldBeUpdated.setSectorName(itemLocalRequest.sectorName());
        itemLocalShouldBeUpdated.setPosition(itemLocalRequest.position());
        itemLocalShouldBeUpdated.setShelf(itemLocalRequest.shelf());

        ItemLocal itemUpdated = itemLocalRepository.save(itemLocalShouldBeUpdated);

        ItemLocalResponse itemLocalResponse = ItemLocalResponseMapper.itemLocalMap(itemUpdated);
        return itemLocalResponse;
    }

    @Transactional
    public ItemLocalResponse patchUpdate(Long id, ItemLocalPatchRequest itemLocalPatchRequest) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id não pode ser nulo ou menor que 1");
        }

        ItemLocal itemLocalShouldBeUpdated = itemLocalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Local não encontrado"));

        itemLocalShouldBeUpdated.setSectorName(
                itemLocalPatchRequest.sectorName() == null
                        ? itemLocalShouldBeUpdated.getSectorName()
                        : itemLocalPatchRequest.sectorName());

        itemLocalShouldBeUpdated
                .setPosition(itemLocalPatchRequest.position() == null ? itemLocalShouldBeUpdated.getPosition()
                        : itemLocalPatchRequest.position());

        itemLocalShouldBeUpdated
                .setShelf(itemLocalPatchRequest.shelf() == null
                        ? itemLocalShouldBeUpdated.getShelf()
                        : itemLocalPatchRequest.shelf());

        ItemLocal itemUpdated = itemLocalRepository.save(itemLocalShouldBeUpdated);

        ItemLocalResponse itemLocalResponse = ItemLocalResponseMapper.itemLocalMap(itemUpdated);
        return itemLocalResponse;
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            itemLocalRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
