package com.sig.sistema_gerenciador_inventario.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
        if (itemLocalRequest.sectorName() == null || itemLocalRequest.sectorName().isBlank()) {
            throw new IllegalArgumentException("Nome do setor não pode ser vazio");
        }
        if (itemLocalRequest.shelf() == null || itemLocalRequest.shelf().isBlank()) {
            throw new IllegalArgumentException("Lote não pode ser vazio");
        }
        if (itemLocalRequest.position() == null) {
            throw new IllegalArgumentException("Posição não pode ser nulo");
        }
        ItemLocal itemLocalCreated = new ItemLocal(itemLocalRequest.sectorName(), itemLocalRequest.position(),
                itemLocalRequest.shelf());
        itemLocalCreated = itemLocalRepository.save(itemLocalCreated);
        ItemLocalResponse itemLocalResponse = new ItemLocalResponse(itemLocalCreated.getLocal_id(),itemLocalCreated.getSectorName(),
                itemLocalCreated.getPosition(), itemLocalCreated.getShelf());
        return itemLocalResponse;
    }

    public ItemLocalResponse findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id não pode ser nulo ou menor que 1");
        }
        ItemLocal itemLocal = itemLocalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Local não encontrado"));
        ItemLocalResponse itemLocalResponse = new ItemLocalResponse(itemLocal.getLocal_id(),itemLocal.getSectorName(), itemLocal.getPosition(),
                itemLocal.getShelf());
        return itemLocalResponse;
    }

    public List<ItemLocalResponse> findAll() {
        return itemLocalRepository.findAll()
                .stream()
                .map(i -> new ItemLocalResponse(i.getLocal_id(),
                        i.getSectorName(),
                        i.getPosition(),
                        i.getShelf()))
                .toList();
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
        
        ItemLocalResponse itemLocalResponse = new ItemLocalResponse(itemUpdated.getLocal_id(),itemUpdated.getSectorName(),
                itemUpdated.getPosition(), itemUpdated.getShelf());
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
        ItemLocalResponse itemLocalResponse = new ItemLocalResponse(itemUpdated.getLocal_id(),itemUpdated.getSectorName(),
                itemUpdated.getPosition(), itemUpdated.getShelf());
        return itemLocalResponse;
    }

    @Transactional
    public void deleteById(Long id) {
        if (!itemLocalRepository.existsById(id)) {
            throw new IllegalArgumentException("Id invalido");
        }
        itemLocalRepository.deleteById(id);
    }

}
