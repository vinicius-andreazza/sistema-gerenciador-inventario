package com.sig.sistema_gerenciador_inventario.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldNotReturnAllUsersWhenIsUnsecured() throws Exception {
        mockMvc.perform(get("/user/all"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldNotReturnUserByIdWhenIsUnsecured() throws Exception {
        mockMvc.perform(get("/user/1"))
        .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    void shouldReturnAllUsers() throws Exception {
        mockMvc.perform(get("/user/all"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldReturnUserById() throws Exception {
        mockMvc.perform(get("/user/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1));
    }
}
