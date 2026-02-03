package com.sig.sistema_gerenciador_inventario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sig.sistema_gerenciador_inventario.model.Supplier;
import com.sig.sistema_gerenciador_inventario.model.dto.request.SupplierCreateRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.SupplierUpdateRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.SupplierResponse;
import com.sig.sistema_gerenciador_inventario.repository.SupplierRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierResponse create(SupplierCreateRequest supplierCreateRequest) {
        if (supplierCreateRequest.name() == null || supplierCreateRequest.name().isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (supplierCreateRequest.email() == null || supplierCreateRequest.email().isBlank()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }
        Supplier supplierCreated = new Supplier(supplierCreateRequest.name(), supplierCreateRequest.phone(),
                supplierCreateRequest.email(), supplierCreateRequest.rawMaterials());
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

    public SupplierResponse update(SupplierUpdateRequest supplierUpdateRequest) {
        if (supplierUpdateRequest.id() == null || supplierUpdateRequest.id() <= 0) {
            throw new IllegalArgumentException("Id não pode ser nulo ou do que 1");
        }
        Supplier supplierShouldBeUpdated = supplierRepository.findById(supplierUpdateRequest.id())
                .orElseThrow(() -> new EntityNotFoundException("Id invalido"));

        supplierShouldBeUpdated.setName(supplierUpdateRequest.name() != null 
                ? supplierUpdateRequest.name()
                : supplierShouldBeUpdated.getName());

        supplierShouldBeUpdated.setEmail(supplierUpdateRequest.email() != null 
                ? supplierUpdateRequest.email()
                : supplierShouldBeUpdated.getEmail());

        supplierShouldBeUpdated.setPhone(supplierUpdateRequest.phone() != null 
                ? supplierUpdateRequest.phone()
                : supplierShouldBeUpdated.getPhone());
                
        supplierShouldBeUpdated
                .setRawMaterial(supplierUpdateRequest.rawMaterials() != null 
                        ? supplierUpdateRequest.rawMaterials()
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
