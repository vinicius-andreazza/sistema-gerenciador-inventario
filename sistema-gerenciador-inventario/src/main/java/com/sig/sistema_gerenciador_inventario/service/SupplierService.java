package com.sig.sistema_gerenciador_inventario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sig.sistema_gerenciador_inventario.model.Supplier;
import com.sig.sistema_gerenciador_inventario.model.dto.request.SupplierRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.SupplierPatchRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.SupplierResponse;
import com.sig.sistema_gerenciador_inventario.repository.SupplierRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierResponse create(SupplierRequest supplierRequest) {
        if (supplierRequest.name() == null || supplierRequest.name().isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (supplierRequest.email() == null || supplierRequest.email().isBlank()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }
        Supplier supplierCreated = new Supplier(supplierRequest.name(), supplierRequest.phone(),
                supplierRequest.email(), supplierRequest.rawMaterials());
        supplierCreated = supplierRepository.save(supplierCreated);
        SupplierResponse response = new SupplierResponse(supplierCreated.getSupplier_id(), supplierCreated.getName(),
                supplierCreated.getPhone(), supplierCreated.getEmail());
        return response;
    }

    public List<SupplierResponse> findAll() {
        return supplierRepository.findAll().stream()
                .map(s -> new SupplierResponse(s.getSupplier_id(), s.getName(), s.getPhone(), s.getEmail())).toList();
    }

    public SupplierResponse findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id não pode ser nulo ou do que 1");
        }
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id invalido"));
        return new SupplierResponse(supplier.getSupplier_id(), supplier.getName(), supplier.getPhone(),
                supplier.getEmail());
    }

    public SupplierResponse putUpdate(Long id, SupplierRequest supplierRequest) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id não pode ser nulo ou menor do que 1");
        }
        if (supplierRequest.name() == null || supplierRequest.name().isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (supplierRequest.email() == null || supplierRequest.email().isBlank()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }
        Supplier supplierShouldBeUpdated = supplierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id invalido"));

        supplierShouldBeUpdated.setName(supplierRequest.name());
        supplierShouldBeUpdated.setEmail(supplierRequest.email());
        supplierShouldBeUpdated.setPhone(supplierRequest.phone());      
        supplierShouldBeUpdated.setRawMaterial(supplierRequest.rawMaterials());

        Supplier supplierUpdated = supplierRepository.save(supplierShouldBeUpdated);

        return new SupplierResponse(supplierUpdated.getSupplier_id(), supplierUpdated.getName(),
                supplierUpdated.getPhone(), supplierUpdated.getEmail());
    }

    public SupplierResponse patchUpdate(Long id, SupplierPatchRequest supplierPatchRequest) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id não pode ser nulo ou do que 1");
        }
        Supplier supplierShouldBeUpdated = supplierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id invalido"));

        supplierShouldBeUpdated.setName(supplierPatchRequest.name() != null 
                ? supplierPatchRequest.name()
                : supplierShouldBeUpdated.getName());

        supplierShouldBeUpdated.setEmail(supplierPatchRequest.email() != null 
                ? supplierPatchRequest.email()
                : supplierShouldBeUpdated.getEmail());

        supplierShouldBeUpdated.setPhone(supplierPatchRequest.phone() != null 
                ? supplierPatchRequest.phone()
                : supplierShouldBeUpdated.getPhone());
                
        supplierShouldBeUpdated
                .setRawMaterial(supplierPatchRequest.rawMaterials() != null 
                        ? supplierPatchRequest.rawMaterials()
                        : supplierShouldBeUpdated.getRawMaterial());

        Supplier supplierUpdated = supplierRepository.save(supplierShouldBeUpdated);

        return new SupplierResponse(supplierUpdated.getSupplier_id(), supplierUpdated.getName(),
                supplierUpdated.getPhone(), supplierUpdated.getEmail());
    }

    public void deleteById(Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new EntityNotFoundException("Id invalido");
        }
        supplierRepository.deleteById(id);
    }
}
