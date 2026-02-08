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

import tools.jackson.databind.ObjectMapper;
import com.sig.sistema_gerenciador_inventario.model.ItemLocal;
import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ProductPatchRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ProductRequest;
import com.sig.sistema_gerenciador_inventario.model.enums.CategoryItem;
import com.sig.sistema_gerenciador_inventario.model.enums.StatusItem;
import com.sig.sistema_gerenciador_inventario.model.enums.TypeItem;
import com.sig.sistema_gerenciador_inventario.repository.ItemRepository;
import com.sig.sistema_gerenciador_inventario.repository.ProductRepository;
import com.sig.sistema_gerenciador_inventario.service.ItemService;
import com.sig.sistema_gerenciador_inventario.service.ProductService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProductRepository productRepository;

    @MockitoBean
    private ItemRepository itemRepository;

    @MockitoBean
    private ProductService productService;

    @MockitoBean
    private ItemService itemService;

    private final String endpoint = "/products";

    @Test
    void shouldNotReturnAllProductWhenIsUnsecured() throws Exception {
        mockMvc.perform(get(endpoint))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotReturnProductByIdWhenIsUnsecured() throws Exception {
        mockMvc.perform(get(endpoint + "/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotCreateProductByIdWhenIsUnsecured() throws Exception {
        ProductRequest productRequest = generateGenericRequest();
        mockMvc.perform(post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productRequest))
        )
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotUpdateProductWhenIsUnsecured() throws Exception {
        ProductRequest productRequest = generateGenericRequest();
        mockMvc.perform(put(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productRequest))
        )
            .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotPatchUpdateProductWhenIsUnsecured() throws Exception {
        ProductPatchRequest productRequest = generateGenericPatchRequest();
        mockMvc.perform(patch(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productRequest))
        )
            .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotDeleteProductWhenIsUnsecured() throws Exception {
        mockMvc.perform(delete(endpoint + "/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldAllProductWhenIsNormalUser() throws Exception {
        mockMvc.perform(get(endpoint))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldReturnProductByIdWhenIsNormalUser() throws Exception {
        mockMvc.perform(get(endpoint + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldCreateProductByIdWhenIsNormalUser() throws Exception {
        ProductRequest productRequest = generateGenericRequest();
        mockMvc.perform(post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productRequest))
        )
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldUpdateProductWhenIsNormalUser() throws Exception {
        ProductRequest productRequest = generateGenericRequest();
        mockMvc.perform(put(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productRequest))
        )
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldPatchUpdateProductWhenIsNormalUser() throws Exception {
        ProductPatchRequest productRequest = generateGenericPatchRequest();
        mockMvc.perform(patch(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productRequest))
        )
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldDeleteProductWhenIsNormalUser() throws Exception {
        mockMvc.perform(delete(endpoint + "/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnAllProduct() throws Exception {
        mockMvc.perform(get(endpoint))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnProductById() throws Exception {
        mockMvc.perform(get(endpoint +"/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldCreateProduct() throws Exception {
        ProductRequest productRequest = generateGenericRequest();
        mockMvc.perform(post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productRequest))
        )
            .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldPutUpdateProduct() throws Exception {
        ProductRequest productRequest = generateGenericRequest();
        mockMvc.perform(put(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productRequest))
        )
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldPatchUpdateProduct() throws Exception {
        ProductPatchRequest productRequest = generateGenericPatchRequest();
        mockMvc.perform(patch(endpoint+"/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productRequest))
        )
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldDeleteProduct() throws Exception {
        mockMvc.perform(delete(endpoint +"/1"))
                .andExpect(status().isNoContent());
    }

    private ProductRequest generateGenericRequest() {
        return new ProductRequest(
                "Produto A",
                CategoryItem.Embalagem,
                TypeItem.PRODUTO,
                "Descrição",
                10,
                2,
                "UN",
                StatusItem.ATIVO,
                new User(),
                new ItemLocal(),
                100.0,
                10.0,
                5.0,
                3.0,
                2.0);
    }

    private ProductPatchRequest generateGenericPatchRequest() {
        return new ProductPatchRequest(null, null, null, null,null,null,null,null,null,null,150.0,null,null,null,null);
    }

    
}
