package com.sig.sistema_gerenciador_inventario.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.sig.sistema_gerenciador_inventario.model.Supplier;
import com.sig.sistema_gerenciador_inventario.model.dto.request.SupplierRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.SupplierPatchRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.SupplierResponse;
import com.sig.sistema_gerenciador_inventario.repository.SupplierRepository;

import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
@ActiveProfiles("test")
public class SupplierServiceTest {
    @Autowired
    private SupplierService supplierService;

    @MockitoBean
    private SupplierRepository supplierRepository;

    @Test
    void shouldCreateSupplier() {
        SupplierRequest supplierRequest = new SupplierRequest("Marchetti", "(47) 99231-5863",
                "marchetti@gmail.com", null);
        Supplier supplierCreate = new Supplier(supplierRequest.name(), supplierRequest.phone(), supplierRequest.email(),
                supplierRequest.rawMaterials());
        SupplierResponse supplierResponseExcepted = new SupplierResponse(null, supplierRequest.name(),
                supplierRequest.phone(), supplierRequest.email());

        when(supplierRepository.save(supplierCreate)).thenReturn(supplierCreate);

        SupplierResponse supplierResponse = supplierService.create(supplierRequest);

        verifySupplierResponse(supplierResponse, supplierResponseExcepted);
    }

    @Test
    void shouldGetAllSupplier() {
        Supplier supplier1 = new Supplier("Marchetti", "(47) 99231-5863", "marchetti@gmail.com", null);
        Supplier supplier2 = new Supplier("Rosina Portas", "(47) 22222-5863", "sasasa@gmail.com", null);
        List<Supplier> listSupplier = new ArrayList<>();
        listSupplier.add(supplier1);
        listSupplier.add(supplier2);
        when(supplierRepository.findAll()).thenReturn(listSupplier);

        List<SupplierResponse> supplierResponseExcepted = new ArrayList<>();
        supplierResponseExcepted.add(new SupplierResponse(supplier1.getSupplier_id(), supplier1.getName(),
                supplier1.getPhone(), supplier1.getEmail()));
        supplierResponseExcepted.add(new SupplierResponse(supplier2.getSupplier_id(), supplier2.getName(),
                supplier2.getPhone(), supplier2.getEmail()));

        List<SupplierResponse> supplierResponse = supplierService.findAll();

        for (int i = 0; i < supplierResponseExcepted.size(); i++) {
            verifySupplierResponse(supplierResponse.get(i), supplierResponseExcepted.get(i));
        }
    }

    @Test
    void shouldGetSupplierById() {
        Supplier supplier = new Supplier(1L, "Marchetti", "(47) 99231-5863", "marchetti@gmail.com", null);
        SupplierResponse supplierResponseExcepted = new SupplierResponse(supplier.getSupplier_id(), supplier.getName(),
                supplier.getPhone(), supplier.getEmail());
        Long id = 1L;
        when(supplierRepository.findById(id)).thenReturn(Optional.of(supplier));

        SupplierResponse supplierResponse = supplierService.findById(id);

        verifySupplierResponse(supplierResponse, supplierResponseExcepted);
    }

    @Test
    void shouldPutUpdateSupplier() {
        SupplierRequest supplierRequest = new SupplierRequest("Marchetti", "(47) 99231-5863", "marchetti@gmail.com", null);
        Long id = 1L;
        Supplier supplier = new Supplier(id, supplierRequest.name(), supplierRequest.phone(), supplierRequest.email(), supplierRequest.rawMaterials());

        when(supplierRepository.findById(id))
                .thenReturn(Optional.of(new Supplier(id, "Port", supplierRequest.phone(), supplierRequest.email(), supplierRequest.rawMaterials())));
        when(supplierRepository.save(supplier)).thenReturn(supplier);

        SupplierResponse supplierResponse = supplierService.putUpdate(id, supplierRequest);
        SupplierResponse supplierResponseExcepted = new SupplierResponse(supplier.getSupplier_id(), supplier.getName(),
        supplier.getPhone(), supplier.getEmail());

        verify(supplierRepository, times(1)).save(supplier);
        verifySupplierResponse(supplierResponse, supplierResponseExcepted);
    }

    @Test
    void shouldPatchUpdateSupplier() {
        SupplierPatchRequest supplierPatchRequest = new SupplierPatchRequest("Marchetti", "(47) 99231-5863", "marchetti@gmail.com", null);
        Long id = 1L;
        Supplier supplier = new Supplier(id, supplierPatchRequest.name(), supplierPatchRequest.phone(), supplierPatchRequest.email(), supplierPatchRequest.rawMaterials());

        when(supplierRepository.findById(id))
                .thenReturn(Optional.of(new Supplier(id, "Port", supplierPatchRequest.phone(), supplierPatchRequest.email(), supplierPatchRequest.rawMaterials())));
        when(supplierRepository.save(supplier)).thenReturn(supplier);

        SupplierResponse supplierResponse = supplierService.patchUpdate(id, supplierPatchRequest);
        SupplierResponse supplierResponseExcepted = new SupplierResponse(supplier.getSupplier_id(), supplier.getName(),
        supplier.getPhone(), supplier.getEmail());

        verify(supplierRepository, times(1)).save(supplier);
        verifySupplierResponse(supplierResponse, supplierResponseExcepted);
    }

    @Test
    void shouldDeleteSupplierById() {
        Supplier supplier = new Supplier(1L, "Marchetti", "(47) 99231-5863", "marchetti@gmail.com", null);

        when(supplierRepository.existsById(supplier.getSupplier_id())).thenReturn(true);

        supplierService.deleteById(supplier.getSupplier_id());
    }

    @Test
    void shouldCreateSupplierWhenPhoneIsNull() {
        SupplierRequest supplierRequest = new SupplierRequest("Marchetti", null,
                "marchetti@gmail.com", null);
        Supplier supplierCreate = new Supplier(supplierRequest.name(), supplierRequest.phone(), supplierRequest.email(),
                supplierRequest.rawMaterials());
        SupplierResponse supplierResponseExcepted = new SupplierResponse(null, supplierRequest.name(),
                supplierRequest.phone(), supplierRequest.email());

        when(supplierRepository.save(supplierCreate)).thenReturn(supplierCreate);

        SupplierResponse supplierResponse = supplierService.create(supplierRequest);

        verifySupplierResponse(supplierResponse, supplierResponseExcepted);
    }

    @Test
    void shouldNotCreateSupplierWhenNameOrEmailIsNull() {
        SupplierRequest supplierRequest = new SupplierRequest("Marchetti", "(47) 99231-5863",
                null, null);
            SupplierRequest supplierRequest1 = new SupplierRequest(null, "(47) 99231-5863",
                "marchetti@gmail.com", null);

        assertThrows(IllegalArgumentException.class, () -> supplierService.create(supplierRequest));
        assertThrows(IllegalArgumentException.class, () -> supplierService.create(supplierRequest1));
    }

    @Test
    void shouldNotGetSupplierByIdWhenIdIsNull(){
        assertThrows(IllegalArgumentException.class, () -> supplierService.findById(null));
    }

    @Test
    void shouldNotGetSupplierByIdWhenIdNotExist(){
        when(supplierRepository.findById(1L)).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> supplierService.findById(1L));
    }

    @Test
    void shouldNotPutUpdatedWhenIdIsNullOrBlank(){
        assertThrows(IllegalArgumentException.class,() -> supplierService.putUpdate(null,new SupplierRequest( null, null, null, null)));
        assertThrows(IllegalArgumentException.class,() -> supplierService.putUpdate(-1L,new SupplierRequest( null, null, null, null)));
    }

    @Test
    void shouldNotPutUpdatedWhenNameOrEmailIsNull(){
        assertThrows(IllegalArgumentException.class,() -> supplierService.putUpdate(null,new SupplierRequest( "null", "null", null, null)));
        assertThrows(IllegalArgumentException.class,() -> supplierService.putUpdate(-1L,new SupplierRequest( null, "null", "null", null)));
    }

    private void verifySupplierResponse(SupplierResponse response1, SupplierResponse response2) {
        assertEquals(response1.id(), response2.id());
        assertEquals(response1.name(), response2.name());
        assertEquals(response1.phone(), response2.phone());
        assertEquals(response1.email(), response2.email());
    }
}
