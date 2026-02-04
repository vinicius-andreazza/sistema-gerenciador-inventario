package com.sig.sistema_gerenciador_inventario.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.sig.sistema_gerenciador_inventario.model.dto.request.ItemLocalRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ItemLocalPatchRequest;
import com.sig.sistema_gerenciador_inventario.repository.ItemLocalRepository;
import com.sig.sistema_gerenciador_inventario.service.ItemLocalService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import tools.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ItemLocalControllerServiceTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ItemLocalService itemLocalService;

    @MockitoBean
    private ItemLocalRepository itemLocalRepository;

    private final String endpoint = "/locals";

    @Test
    void shouldNotReturnAllLocalsWhenIsUnsecured() throws Exception {
        mockMvc.perform(get(endpoint))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotReturnLocalByIdWhenIsUnsecured() throws Exception {
        mockMvc.perform(get(endpoint + "/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotCreateLocalByIdWhenIsUnsecured() throws Exception {
        ItemLocalRequest localRequest = new ItemLocalRequest("Estoque", 1, "A");
        mockMvc.perform(post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(localRequest))
        )
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotPutUpdateLocalWhenIsUnsecured() throws Exception {
        ItemLocalRequest localRequest = new ItemLocalRequest("Estoque", 1, "A");
        mockMvc.perform(put(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(localRequest))
        )
            .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotPatchUpdateLocalWhenIsUnsecured() throws Exception {
        ItemLocalPatchRequest localRequest = new ItemLocalPatchRequest("Estoque", 1, "A");
        mockMvc.perform(patch(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(localRequest))
        )
            .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotDeleteLocalWhenIsUnsecured() throws Exception {
        mockMvc.perform(delete(endpoint + "/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldAllLocalsWhenIsNormalLocal() throws Exception {
        mockMvc.perform(get(endpoint))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldReturnLocalByIdWhenIsNormalLocal() throws Exception {
        mockMvc.perform(get(endpoint + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldCreateLocalByIdWhenIsNormalLocal() throws Exception {
        ItemLocalRequest localRequest = new ItemLocalRequest("Estoque", 1, "A");
        mockMvc.perform(post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(localRequest))
        )
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldPutUpdateLocalWhenIsNormalLocal() throws Exception {
        ItemLocalRequest localRequest = new ItemLocalRequest("Estoque", 1, "A");
        mockMvc.perform(put(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(localRequest))
        )
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldPatchUpdateLocalWhenIsNormalLocal() throws Exception {
        ItemLocalPatchRequest localRequest = new ItemLocalPatchRequest("Estoque", 1, "A");
        mockMvc.perform(put(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(localRequest))
        )
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldDeleteLocalWhenIsNormalLocal() throws Exception {
        mockMvc.perform(delete(endpoint + "/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnAllLocals() throws Exception {
        mockMvc.perform(get(endpoint))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnLocalById() throws Exception {
        mockMvc.perform(get(endpoint +"/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldCreateLocal() throws Exception {
        ItemLocalRequest localRequest = new ItemLocalRequest("Estoque", 1, "A");
        mockMvc.perform(post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(localRequest))
        )
            .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldPutUpdateLocal() throws Exception {
        ItemLocalRequest localRequest = new ItemLocalRequest("Estoque", 1, "A");
        mockMvc.perform(put(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(localRequest))
        )
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldPatchUpdateLocal() throws Exception {
        ItemLocalPatchRequest localRequest = new ItemLocalPatchRequest("Estoque", 1, "A");
        mockMvc.perform(put(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(localRequest))
        )
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldDeleteLocal() throws Exception {
        mockMvc.perform(delete(endpoint +"/1"))
                .andExpect(status().isNoContent());
    }
}
