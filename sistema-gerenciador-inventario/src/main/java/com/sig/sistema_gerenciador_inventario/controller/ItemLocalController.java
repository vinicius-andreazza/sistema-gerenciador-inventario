package com.sig.sistema_gerenciador_inventario.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sig.sistema_gerenciador_inventario.model.dto.request.ItemLocalRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ItemLocalPatchRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.ItemLocalResponse;
import com.sig.sistema_gerenciador_inventario.service.ItemLocalService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    public ResponseEntity<ItemLocalResponse> createLocal(@RequestBody ItemLocalRequest itemLocalRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemLocalService.create(itemLocalRequest));
    }

    @GetMapping("")
    public ResponseEntity<List<ItemLocalResponse>> findAll() throws Exception {
        return ResponseEntity.ok(itemLocalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemLocalResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(itemLocalService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemLocalResponse> putUpdate(@PathVariable Long id, @RequestBody ItemLocalRequest itemLocalRequest) {
        return ResponseEntity.ok(itemLocalService.putUpdate(id, itemLocalRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ItemLocalResponse> patchUpdate(@PathVariable Long id, @RequestBody ItemLocalPatchRequest itemLocalPatchRequest) {
        return ResponseEntity.ok(itemLocalService.patchUpdate(id, itemLocalPatchRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        itemLocalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
