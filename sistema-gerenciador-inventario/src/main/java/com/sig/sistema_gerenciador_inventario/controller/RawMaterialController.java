package com.sig.sistema_gerenciador_inventario.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sig.sistema_gerenciador_inventario.model.dto.request.RawMaterialPatchRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.RawMaterialRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.RawMaterialResponse;
import com.sig.sistema_gerenciador_inventario.service.RawMaterialService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rawMaterials")
public class RawMaterialController {
    private final RawMaterialService rawMaterialService;

    @PostMapping("")
    public ResponseEntity<RawMaterialResponse> create(@RequestBody RawMaterialRequest rawMaterialRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(rawMaterialService.create(rawMaterialRequest));
    }

    @GetMapping("")
    public ResponseEntity<List<RawMaterialResponse>> findAll() {
        return ResponseEntity.ok(rawMaterialService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RawMaterialResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(rawMaterialService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RawMaterialResponse> putUpdate(@PathVariable Long id, @RequestBody RawMaterialRequest request) {
        return ResponseEntity.ok(rawMaterialService.putUpdate(id, request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RawMaterialResponse> patchUpdate(@PathVariable Long id, @RequestBody RawMaterialPatchRequest request) {
        return ResponseEntity.ok(rawMaterialService.patchUpdate(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        rawMaterialService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
