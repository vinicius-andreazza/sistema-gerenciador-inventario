package com.sig.sistema_gerenciador_inventario.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.sig.sistema_gerenciador_inventario.model.ItemLocal;
import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.model.dto.request.RawMaterialPatchRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.RawMaterialRequest;
import com.sig.sistema_gerenciador_inventario.model.enums.CategoryItem;
import com.sig.sistema_gerenciador_inventario.model.enums.StatusItem;
import com.sig.sistema_gerenciador_inventario.model.enums.TypeItem;
import com.sig.sistema_gerenciador_inventario.repository.ItemRepository;
import com.sig.sistema_gerenciador_inventario.repository.RawMaterialRepository;
import com.sig.sistema_gerenciador_inventario.service.ItemService;
import com.sig.sistema_gerenciador_inventario.service.RawMaterialService;

import tools.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RawMaterialControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private RawMaterialRepository rawMaterialRepository;

    @MockitoBean
    private ItemRepository itemRepository;

    @MockitoBean
    private RawMaterialService rawMaterialService;

    @MockitoBean
    private ItemService itemService;

    private final String endpoint = "/rawMaterials";

    @Test
    void shouldNotReturnAllRawMaterialWhenIsUnsecured() throws Exception {
        mockMvc.perform(get(endpoint))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotReturnRawMaterialByIdWhenIsUnsecured() throws Exception {
        mockMvc.perform(get(endpoint + "/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotCreateRawMaterialByIdWhenIsUnsecured() throws Exception {
        RawMaterialRequest productRequest = generateGenericRequest();
        mockMvc.perform(post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productRequest))
        )
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotUpdateRawMaterialWhenIsUnsecured() throws Exception {
        RawMaterialRequest productRequest = generateGenericRequest();
        mockMvc.perform(put(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productRequest))
        )
            .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotPatchUpdateRawMaterialWhenIsUnsecured() throws Exception {
        RawMaterialPatchRequest productRequest = generateGenericPatchRequest();
        mockMvc.perform(patch(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productRequest))
        )
            .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotDeleteRawMaterialWhenIsUnsecured() throws Exception {
        mockMvc.perform(delete(endpoint + "/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldAllRawMaterialWhenIsNormalUser() throws Exception {
        mockMvc.perform(get(endpoint))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldReturnRawMaterialByIdWhenIsNormalUser() throws Exception {
        mockMvc.perform(get(endpoint + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldCreateRawMaterialByIdWhenIsNormalUser() throws Exception {
        RawMaterialRequest productRequest = generateGenericRequest();
        mockMvc.perform(post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productRequest))
        )
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldUpdateRawMaterialWhenIsNormalUser() throws Exception {
        RawMaterialRequest productRequest = generateGenericRequest();
        mockMvc.perform(put(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productRequest))
        )
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldPatchUpdateRawMaterialWhenIsNormalUser() throws Exception {
        RawMaterialPatchRequest productRequest = generateGenericPatchRequest();
        mockMvc.perform(patch(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productRequest))
        )
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldDeleteRawMaterialWhenIsNormalUser() throws Exception {
        mockMvc.perform(delete(endpoint + "/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnAllRawMaterial() throws Exception {
        mockMvc.perform(get(endpoint))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnRawMaterialById() throws Exception {
        mockMvc.perform(get(endpoint +"/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldCreateRawMaterial() throws Exception {
        RawMaterialRequest productRequest = generateGenericRequest();
        mockMvc.perform(post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productRequest))
        )
            .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldPutUpdateRawMaterial() throws Exception {
        RawMaterialRequest productRequest = generateGenericRequest();
        mockMvc.perform(put(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productRequest))
        )
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldPatchUpdateRawMaterial() throws Exception {
        RawMaterialPatchRequest productRequest = generateGenericPatchRequest();
        mockMvc.perform(patch(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productRequest))
        )
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldDeleteRawMaterial() throws Exception {
        mockMvc.perform(delete(endpoint +"/1"))
                .andExpect(status().isNoContent());
    }

    private RawMaterialRequest generateGenericRequest() {
        return new RawMaterialRequest(
                "Matéria-prima A",
                CategoryItem.EMBALAGEM,
                TypeItem.MATERIA_PRIMA,
                "Descrição teste",
                100,
                10,
                "KG",
                StatusItem.ATIVO,
                1L,
                1L,
                50,
                12.5,
                Set.of()
        );
    }

    private RawMaterialPatchRequest generateGenericPatchRequest() {
        return new RawMaterialPatchRequest(null,null,null,null,null,null,null,null,null,null,80,20.0,null);
    }
}
