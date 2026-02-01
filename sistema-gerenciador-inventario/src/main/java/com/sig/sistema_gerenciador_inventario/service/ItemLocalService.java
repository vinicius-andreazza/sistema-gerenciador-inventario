package com.sig.sistema_gerenciador_inventario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sig.sistema_gerenciador_inventario.model.ItemLocal;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ItemLocalCreateRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ItemLocalUpdateRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.ItemLocalResponse;
import com.sig.sistema_gerenciador_inventario.repository.ItemLocalRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemLocalService {
    private final ItemLocalRepository itemLocalRepository;

    public ItemLocalResponse create(ItemLocalCreateRequest itemLocalRequest) {
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
        ItemLocalResponse itemLocalResponse = new ItemLocalResponse(itemLocalCreated.getSectorName(),
                itemLocalCreated.getPosition(), itemLocalCreated.getShelf());
        return itemLocalResponse;
    }

    public ItemLocalResponse findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id não pode ser nulo ou menor que 1");
        }
        ItemLocal itemLocal = itemLocalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Local não encontrado"));
        ItemLocalResponse itemLocalResponse = new ItemLocalResponse(itemLocal.getSectorName(), itemLocal.getPosition(),
                itemLocal.getShelf());
        return itemLocalResponse;
    }

    public List<ItemLocalResponse> findAll() {
        return itemLocalRepository.findAll()
                .stream()
                .map(i -> new ItemLocalResponse(
                        i.getSectorName(),
                        i.getPosition(),
                        i.getShelf()))
                .toList();
    }

    @Transactional
    public ItemLocalResponse update(ItemLocalUpdateRequest itemLocalUpdateRequest) {
        if (itemLocalUpdateRequest.id() == null || itemLocalUpdateRequest.id() <= 0) {
            throw new IllegalArgumentException("Id não pode ser nulo ou menor que 1");
        }

        ItemLocal itemLocalShouldBeUpdated = itemLocalRepository.findById(itemLocalUpdateRequest.id())
                .orElseThrow(() -> new EntityNotFoundException("Local não encontrado"));


        itemLocalShouldBeUpdated.setSectorName(
                itemLocalUpdateRequest.sectorName() == null || itemLocalUpdateRequest.sectorName().isBlank()
                        ? itemLocalShouldBeUpdated.getSectorName()
                        : itemLocalUpdateRequest.sectorName());

        itemLocalShouldBeUpdated
                .setPosition(itemLocalUpdateRequest.position() == null ? itemLocalShouldBeUpdated.getPosition()
                        : itemLocalUpdateRequest.position());

        itemLocalShouldBeUpdated
                .setShelf(itemLocalUpdateRequest.shelf() == null || itemLocalUpdateRequest.shelf().isBlank()
                        ? itemLocalShouldBeUpdated.getShelf()
                        : itemLocalUpdateRequest.shelf());


        ItemLocal itemUpdated = itemLocalRepository.save(itemLocalShouldBeUpdated);
        ItemLocalResponse itemLocalResponse = new ItemLocalResponse(itemUpdated.getSectorName(),
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
