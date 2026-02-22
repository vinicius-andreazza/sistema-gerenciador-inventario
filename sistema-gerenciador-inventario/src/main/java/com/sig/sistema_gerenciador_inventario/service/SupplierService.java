package com.sig.sistema_gerenciador_inventario.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sig.sistema_gerenciador_inventario.mapper.models.SupplierMapper;
import com.sig.sistema_gerenciador_inventario.mapper.response.SupplierResponseMapper;
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

    public Long count(){
        return supplierRepository.count();
    }

    public SupplierResponse create(SupplierRequest supplierRequest) {
        Supplier supplierCreated = SupplierMapper.supplierMap(supplierRequest);
        supplierCreated = supplierRepository.save(supplierCreated);
        SupplierResponse response = SupplierResponseMapper.supplierResponseMap(supplierCreated);
        return response;
    }

    public Page<SupplierResponse> findAll(Pageable pageable) {
        return supplierRepository.findAll(pageable)
                .map(SupplierResponseMapper::supplierResponseMap);
    }

    public SupplierResponse findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id não pode ser nulo ou do que 1");
        }
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id invalido"));
        return SupplierResponseMapper.supplierResponseMap(supplier);
    }

    public SupplierResponse putUpdate(Long id, SupplierRequest supplierRequest) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id não pode ser nulo ou menor do que 1");
        }
        Supplier supplierShouldBeUpdated = supplierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id invalido"));

        supplierShouldBeUpdated.setName(supplierRequest.name());
        supplierShouldBeUpdated.setEmail(supplierRequest.email());
        supplierShouldBeUpdated.setPhone(supplierRequest.phone());      
        supplierShouldBeUpdated.setRawMaterial(supplierRequest.rawMaterials());

        Supplier supplierUpdated = supplierRepository.save(supplierShouldBeUpdated);

        return SupplierResponseMapper.supplierResponseMap(supplierUpdated);
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

        return SupplierResponseMapper.supplierResponseMap(supplierUpdated);
    }

    public void deleteById(Long id) {
        try {
            supplierRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
