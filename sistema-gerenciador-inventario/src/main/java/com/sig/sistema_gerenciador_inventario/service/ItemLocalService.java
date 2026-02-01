package com.sig.sistema_gerenciador_inventario.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sig.sistema_gerenciador_inventario.model.ItemLocal;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ItemLocalCreateRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ItemLocalUpdateRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.ItemLocalResponse;
import com.sig.sistema_gerenciador_inventario.repository.ItemLocalRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemLocalService {
    private final ItemLocalRepository itemLocalRepository;

    public ResponseEntity<ItemLocalResponse> create(ItemLocalCreateRequest itemLocalRequest){
        if(itemLocalRequest.sectorName() == null || itemLocalRequest.sectorName().isBlank()){
            throw new IllegalArgumentException("Nome do setor não pode ser vazio");
        }
        if(itemLocalRequest.shelf() == null  || itemLocalRequest.shelf().isBlank()){
            throw new IllegalArgumentException("Lote não pode ser vazio");
        }
        if(itemLocalRequest.position() == null){
            throw new IllegalArgumentException("Posição não pode ser nulo");
        }
        ItemLocal itemLocalCreated = new ItemLocal(itemLocalRequest.sectorName(), itemLocalRequest.position(), itemLocalRequest.shelf());
        try {
            itemLocalCreated = itemLocalRepository.save(itemLocalCreated);
        } catch (Exception e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
        ItemLocalResponse itemLocalResponse = new ItemLocalResponse(itemLocalCreated.getSectorName(), itemLocalCreated.getPosition(), itemLocalCreated.getShelf());
        return ResponseEntity.status(HttpStatus.CREATED).body(itemLocalResponse);
    }

    public ResponseEntity<ItemLocalResponse> findById(Long id){
        if(id == null || id < 1){
            throw new IllegalArgumentException("Id não pode ser nulo ou menor que 1");
        }
        ItemLocal itemLocal;
        try {
            itemLocal = itemLocalRepository.findById(id).get();
        } catch (Exception e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
        ItemLocalResponse itemLocalResponse = new ItemLocalResponse(itemLocal.getSectorName(), itemLocal.getPosition(), itemLocal.getShelf());
        return ResponseEntity.ok(itemLocalResponse);
    }

    public ResponseEntity<List<ItemLocalResponse>> findAll() throws Exception{
        List<ItemLocalResponse> itemLocalResponse = itemLocalRepository.findAll()
            .stream()
            .map(i -> new ItemLocalResponse(
                    i.getSectorName(),
                    i.getPosition(),
                    i.getShelf()
            ))
            .toList();
        return ResponseEntity.ok(itemLocalResponse);
    }

    @Transactional
    public ResponseEntity<ItemLocalResponse> update(ItemLocalUpdateRequest itemLocalUpdateRequest){
        if(itemLocalUpdateRequest.id() == null || itemLocalUpdateRequest.id() < -1){
            throw new IllegalArgumentException("Id não pode ser nulo ou menor que 1");
        }
        if(!itemLocalRepository.existsById(itemLocalUpdateRequest.id())){
            throw new IllegalArgumentException("Id invalido");
        }
        ItemLocal itemLocalShouldBeUpdated = itemLocalRepository.findById(itemLocalUpdateRequest.id()).get();
        itemLocalShouldBeUpdated.setSectorName(itemLocalUpdateRequest.sectorName().isBlank() ? itemLocalShouldBeUpdated.getSectorName() : itemLocalUpdateRequest.sectorName());
        itemLocalShouldBeUpdated.setPosition(itemLocalUpdateRequest.position() == null ? itemLocalShouldBeUpdated.getPosition() : itemLocalUpdateRequest.position());
        itemLocalShouldBeUpdated.setShelf(itemLocalUpdateRequest.shelf().isBlank() ? itemLocalShouldBeUpdated.getShelf() : itemLocalUpdateRequest.shelf());
        ItemLocalResponse itemLocalResponse;
        try {
            ItemLocal itemUpdated = itemLocalRepository.save(itemLocalShouldBeUpdated);
            itemLocalResponse = new ItemLocalResponse(itemUpdated.getSectorName(), itemUpdated.getPosition(), itemUpdated.getShelf());
        } catch (Exception e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
        return ResponseEntity.ok(itemLocalResponse);
    }

    @Transactional
    public ResponseEntity<?> deleteById(Long id){
        if(!itemLocalRepository.existsById(id)){
            throw new IllegalArgumentException("Id invalido");
        }
        itemLocalRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
