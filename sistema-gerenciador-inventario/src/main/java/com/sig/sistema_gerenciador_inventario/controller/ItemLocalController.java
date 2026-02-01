package com.sig.sistema_gerenciador_inventario.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sig.sistema_gerenciador_inventario.model.dto.request.ItemLocalCreateRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ItemLocalUpdateRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.ItemLocalResponse;
import com.sig.sistema_gerenciador_inventario.service.ItemLocalService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@Controller
@RequestMapping("/locals")
public class ItemLocalController {
    private final ItemLocalService itemLocalService;

    @PostMapping("")
    public ResponseEntity<ItemLocalResponse> createLocal(@RequestBody ItemLocalCreateRequest itemLocalCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemLocalService.create(itemLocalCreateRequest));
    }

    @GetMapping("")
    public ResponseEntity<List<ItemLocalResponse>> findAll() throws Exception {
        return ResponseEntity.ok(itemLocalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemLocalResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(itemLocalService.findById(id));
    }

    @PutMapping("")
    public ResponseEntity<ItemLocalResponse> update(@RequestBody ItemLocalUpdateRequest itemLocalUpdateRequest) {
        return ResponseEntity.ok(itemLocalService.update(itemLocalUpdateRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        itemLocalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
