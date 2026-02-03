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

import com.sig.sistema_gerenciador_inventario.model.dto.request.UserRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.UserPatchRequest;
import com.sig.sistema_gerenciador_inventario.model.enums.UserRole;
import com.sig.sistema_gerenciador_inventario.repository.UserRepository;
import com.sig.sistema_gerenciador_inventario.service.UserService;

import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserRepository userRepository;

    @MockitoBean
    private UserService userService;

    private final String endpoint = "/users";

    @Test
    void shouldNotReturnAllUsersWhenIsUnsecured() throws Exception {
        mockMvc.perform(get(endpoint))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotReturnUserByIdWhenIsUnsecured() throws Exception {
        mockMvc.perform(get(endpoint + "/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotCreateUserByIdWhenIsUnsecured() throws Exception {
        UserRequest userRequest = new UserRequest("abecedario", "12345", UserRole.ROLE_USER);
        mockMvc.perform(post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userRequest))
        )
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotPutUserWhenIsUnsecured() throws Exception {
        UserRequest userRequest = new UserRequest("abecedario", "12345", UserRole.ROLE_USER);
        Long id = 1L;
        mockMvc.perform(put(endpoint +"/"+ id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userRequest))
        )
            .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotPatchUserWhenIsUnsecured() throws Exception {
        UserPatchRequest userRequest = new UserPatchRequest("abecedario", "12345", UserRole.ROLE_USER);
        Long id = 1L;
        mockMvc.perform(patch(endpoint +"/"+ id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userRequest))
        )
            .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotDeleteUserWhenIsUnsecured() throws Exception {
        mockMvc.perform(delete(endpoint + "/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldNotReturnAllUsersWhenIsNormalUser() throws Exception {
        mockMvc.perform(get(endpoint))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldNotReturnUserByIdWhenIsNormalUser() throws Exception {
        mockMvc.perform(get(endpoint + "/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldNotCreateUserByIdWhenIsNormalUser() throws Exception {
        UserRequest userRequest = new UserRequest("abecedario", "12345", UserRole.ROLE_USER);
        mockMvc.perform(post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userRequest))
        )
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldNotPutUserWhenIsNormalUser() throws Exception {
        UserPatchRequest userRequest = new UserPatchRequest("abecedario", "12345", UserRole.ROLE_USER);
        Long id = 1L;
        mockMvc.perform(put(endpoint+"/"+id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userRequest))
        )
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldNotPatchUserWhenIsNormalUser() throws Exception {
        UserRequest userRequest = new UserRequest("abecedario", "12345", UserRole.ROLE_USER);
        Long id = 1L;
        mockMvc.perform(patch(endpoint+"/"+id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userRequest))
        )
            .andExpect(status().isForbidden());
    }


    @Test
    @WithMockUser(roles = "USER")
    void shouldNotDeleteUserWhenIsNormalUser() throws Exception {
        mockMvc.perform(delete(endpoint + "/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnAllUsers() throws Exception {
        mockMvc.perform(get(endpoint))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnUserById() throws Exception {
        mockMvc.perform(get(endpoint + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldCreateUser() throws Exception {
        UserRequest userRequest = new UserRequest("abecedario", "12345", UserRole.ROLE_USER);
        mockMvc.perform(post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userRequest))
        )
            .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldPutUser() throws Exception {
        UserPatchRequest userRequest = new UserPatchRequest("abecedario", "12345", UserRole.ROLE_USER);
        Long id = 1L;
        mockMvc.perform(put(endpoint+"/"+id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userRequest))
        )
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldPatchUser() throws Exception {
        UserRequest userRequest = new UserRequest("abecedario", "12345", UserRole.ROLE_USER);
        Long id = 1L;
        mockMvc.perform(patch(endpoint+"/"+id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userRequest))
        )
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldDeleteUser() throws Exception {
        mockMvc.perform(delete(endpoint + "/1"))
                .andExpect(status().isNoContent());
    }
}
