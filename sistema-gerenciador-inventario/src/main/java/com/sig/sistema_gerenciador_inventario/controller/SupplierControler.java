package com.sig.sistema_gerenciador_inventario.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sig.sistema_gerenciador_inventario.model.dto.request.SupplierPatchRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.SupplierRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.SupplierResponse;
import com.sig.sistema_gerenciador_inventario.service.SupplierService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("suppliers")
public class SupplierControler {
    private final SupplierService supplierService;

    @PostMapping("")
    public ResponseEntity<SupplierResponse> create(@RequestBody SupplierRequest supplierRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.create(supplierRequest));
    }

    @GetMapping("")
    public ResponseEntity<List<SupplierResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(supplierService.findAll(pageable));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.findById(id));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponse> putUpdate(@PathVariable Long id, @RequestBody SupplierRequest supplierRequest) {
        return ResponseEntity.ok(supplierService.putUpdate(id, supplierRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SupplierResponse> patchUpdate(@PathVariable Long id, @RequestBody SupplierPatchRequest supplierPatchRequest) {
        return ResponseEntity.ok(supplierService.patchUpdate(id, supplierPatchRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        supplierService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
