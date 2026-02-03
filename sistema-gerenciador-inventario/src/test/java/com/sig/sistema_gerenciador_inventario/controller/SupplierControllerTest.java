package com.sig.sistema_gerenciador_inventario.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.sig.sistema_gerenciador_inventario.model.dto.request.SupplierCreateRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.SupplierUpdateRequest;
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
        SupplierCreateRequest supplierRequest = new SupplierCreateRequest("Marchetti", "(47) 99231-5863",
                "marchetti@gmail.com", null);
        mockMvc.perform(post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(supplierRequest))
        )
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotUpdateSupplierWhenIsUnsecured() throws Exception {
        SupplierUpdateRequest supplierRequest = new SupplierUpdateRequest(1L,"Marchetti", "(47) 99231-5863",
                "marchetti@gmail.com", null);
        mockMvc.perform(put(endpoint)
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
    void shouldAllSuppliersWhenIsNormalSupplier() throws Exception {
        mockMvc.perform(get(endpoint))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldReturnSupplierByIdWhenIsNormalSupplier() throws Exception {
        mockMvc.perform(get(endpoint + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldCreateSupplierByIdWhenIsNormalSupplier() throws Exception {
        SupplierCreateRequest supplierRequest = new SupplierCreateRequest("Marchetti", "(47) 99231-5863",
                "marchetti@gmail.com", null);
        mockMvc.perform(post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(supplierRequest))
        )
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldUpdateSupplierWhenIsNormalSupplier() throws Exception {
        SupplierUpdateRequest supplierRequest = new SupplierUpdateRequest(1L,"Marchetti", "(47) 99231-5863",
                "marchetti@gmail.com", null);
        mockMvc.perform(put(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(supplierRequest))
        )
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldDeleteSupplierWhenIsNormalSupplier() throws Exception {
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
        SupplierCreateRequest supplierRequest = new SupplierCreateRequest("Marchetti", "(47) 99231-5863",
                "marchetti@gmail.com", null);
        mockMvc.perform(post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(supplierRequest))
        )
            .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldUpdateSupplier() throws Exception {
        SupplierUpdateRequest supplierRequest = new SupplierUpdateRequest(1L,"Marchetti", "(47) 99231-5863",
                "marchetti@gmail.com", null);
        mockMvc.perform(put(endpoint)
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
