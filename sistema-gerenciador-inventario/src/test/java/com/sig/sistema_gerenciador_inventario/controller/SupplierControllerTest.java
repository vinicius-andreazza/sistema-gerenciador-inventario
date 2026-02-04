package com.sig.sistema_gerenciador_inventario.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.sig.sistema_gerenciador_inventario.model.dto.request.SupplierRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.SupplierPatchRequest;
import com.sig.sistema_gerenciador_inventario.repository.SupplierRepository;
import com.sig.sistema_gerenciador_inventario.service.SupplierService;

import tools.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SupplierControllerTest {
     @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private SupplierService supplierService;

    @MockitoBean
    private SupplierRepository supplierRepository;

    private final String endpoint = "/suppliers";

    @Test
    void shouldNotReturnAllSuppliersWhenIsUnsecured() throws Exception {
        mockMvc.perform(get(endpoint))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotReturnSupplierByIdWhenIsUnsecured() throws Exception {
        mockMvc.perform(get(endpoint + "/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotCreateSupplierByIdWhenIsUnsecured() throws Exception {
        SupplierRequest supplierRequest = new SupplierRequest("Marchetti", "(47) 99231-5863",
                "marchetti@gmail.com", null);
        mockMvc.perform(post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(supplierRequest))
        )
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotUpdateSupplierWhenIsUnsecured() throws Exception {
        SupplierRequest supplierRequest = new SupplierRequest("Marchetti", "(47) 99231-5863",
                "marchetti@gmail.com", null);
        mockMvc.perform(put(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(supplierRequest))
        )
            .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotPatchUpdateSupplierWhenIsUnsecured() throws Exception {
        SupplierPatchRequest supplierRequest = new SupplierPatchRequest("Marchetti", "(47) 99231-5863",
                "marchetti@gmail.com", null);
        mockMvc.perform(patch(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(supplierRequest))
        )
            .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotDeleteSupplierWhenIsUnsecured() throws Exception {
        mockMvc.perform(delete(endpoint + "/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldAllSuppliersWhenIsNormalUser() throws Exception {
        mockMvc.perform(get(endpoint))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldReturnSupplierByIdWhenIsNormalUser() throws Exception {
        mockMvc.perform(get(endpoint + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldCreateSupplierByIdWhenIsNormalUser() throws Exception {
        SupplierRequest supplierRequest = new SupplierRequest("Marchetti", "(47) 99231-5863",
                "marchetti@gmail.com", null);
        mockMvc.perform(post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(supplierRequest))
        )
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldUpdateSupplierWhenIsNormalUser() throws Exception {
        SupplierRequest supplierRequest = new SupplierRequest("Marchetti", "(47) 99231-5863",
                "marchetti@gmail.com", null);
        mockMvc.perform(put(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(supplierRequest))
        )
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldPatchUpdateSupplierWhenIsNormalUser() throws Exception {
        SupplierPatchRequest supplierRequest = new SupplierPatchRequest("Marchetti", "(47) 99231-5863",
                "marchetti@gmail.com", null);
        mockMvc.perform(patch(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(supplierRequest))
        )
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldDeleteSupplierWhenIsNormalUser() throws Exception {
        mockMvc.perform(delete(endpoint + "/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnAllSuppliers() throws Exception {
        mockMvc.perform(get(endpoint))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnSupplierById() throws Exception {
        mockMvc.perform(get(endpoint +"/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldCreateSupplier() throws Exception {
        SupplierRequest supplierRequest = new SupplierRequest("Marchetti", "(47) 99231-5863",
                "marchetti@gmail.com", null);
        mockMvc.perform(post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(supplierRequest))
        )
            .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldPutUpdateSupplier() throws Exception {
        SupplierRequest supplierRequest = new SupplierRequest("Marchetti", "(47) 99231-5863",
                "marchetti@gmail.com", null);
        mockMvc.perform(put(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(supplierRequest))
        )
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldPatchUpdateSupplier() throws Exception {
        SupplierPatchRequest supplierRequest = new SupplierPatchRequest("Marchetti", "(47) 99231-5863",
                "marchetti@gmail.com", null);
        mockMvc.perform(patch(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(supplierRequest))
        )
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldDeleteSupplier() throws Exception {
        mockMvc.perform(delete(endpoint +"/1"))
                .andExpect(status().isNoContent());
    }
}
