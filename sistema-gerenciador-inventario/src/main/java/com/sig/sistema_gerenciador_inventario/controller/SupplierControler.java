package com.sig.sistema_gerenciador_inventario.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sig.sistema_gerenciador_inventario.model.dto.request.SupplierCreateRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.SupplierUpdateRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.SupplierResponse;
import com.sig.sistema_gerenciador_inventario.service.SupplierService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("suppliers")
public class SupplierControler {
    private final SupplierService supplierService;

    @PostMapping("")
    public ResponseEntity<SupplierResponse> create(@RequestBody SupplierCreateRequest supplierCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.create(supplierCreateRequest));
    }

    @GetMapping("")
    public ResponseEntity<List<SupplierResponse>> findAll() {
        return ResponseEntity.ok(supplierService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.findById(id));
    }
    
    @PutMapping("")
    public ResponseEntity<SupplierResponse> update(@RequestBody SupplierUpdateRequest supplierUpdateRequest) {
        return ResponseEntity.ok(supplierService.update(supplierUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        supplierService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
