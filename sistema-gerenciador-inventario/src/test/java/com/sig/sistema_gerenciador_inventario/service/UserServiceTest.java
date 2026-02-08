package com.sig.sistema_gerenciador_inventario.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.sig.sistema_gerenciador_inventario.mapper.models.UserMapper;
import com.sig.sistema_gerenciador_inventario.mapper.response.UserResponseMapper;
import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.model.dto.request.UserRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.UserPatchRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.UserResponse;
import com.sig.sistema_gerenciador_inventario.model.enums.UserRole;
import com.sig.sistema_gerenciador_inventario.repository.UserRepository;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {
    @MockitoBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @Test
    void shouldCreateUser(){
        UserRequest userRequest = createGenericUserRequest();

        User userCreated = new User(userRequest.username(), passwordEncoder.encode(userRequest.password()), userRequest.roles());

        UserResponse userExcepted = new UserResponse(null,userCreated.getUsername(), userCreated.getRoles());

        when(userRepository.save(userCreated)).thenReturn(userCreated);

        UserResponse userResponse = userService.create(userRequest);
        

        verifyUserResponse(userResponse, userExcepted);
    }

    @Test
    void shouldGetUser(){
        User userExpected = createGenericUser();
        Long id = 1L;
        when(userRepository.findById(1L)).thenReturn(Optional.of(userExpected));

        UserResponse userActual = userService.findById(id);

        verify(userRepository, times(1)).findById(id);
        verifyUserResponse(userActual, new UserResponse(userExpected.getId(),userExpected.getUsername(), userExpected.getRoles()));
    }

    @Test
    void shouldGetAllUser(){
        User userExpected = createGenericUser();
        User userExpected2 = new User("bernardo", "123", UserRole.ROLE_USER);
        List<User> usersExpecteds = new ArrayList<>();
        usersExpecteds.add(userExpected);
        usersExpecteds.add(userExpected2);
        when(userRepository.findAll()).thenReturn(usersExpecteds);

        List<UserResponse> userActuals = userService.findAll();
        List<UserResponse> usersResponseExpecteds = new ArrayList<>();
        usersResponseExpecteds.add(new UserResponse(userExpected.getId(),userExpected.getUsername(), userExpected.getRoles()));
        usersResponseExpecteds.add(new UserResponse(userExpected.getId(),userExpected2.getUsername(), userExpected2.getRoles()));
        verify(userRepository, times(1)).findAll();
        for(int i=0;i<usersExpecteds.size();i++){
            verifyUserResponse(userActuals.get(i), usersResponseExpecteds.get(i));
        }
    }

    @Test
    void shouldPatchUpdateUser() {
        UserPatchRequest userRequest = new UserPatchRequest("vinicius", "12345", UserRole.ROLE_USER);
        Long id = 1L;
        User userUpdated = new User(userRequest.username(), "teste", userRequest.roles());

        when(passwordEncoder.encode(userRequest.password())).thenReturn("teste");
        when(userRepository.existsById(id)).thenReturn(true);
        when(userRepository.findById(id)).thenReturn(Optional.of(new User(userRequest.username(), "123", UserRole.ROLE_ADMIN)));
        when(userRepository.save(userUpdated)).thenReturn(userUpdated);

        UserResponse userResponse = userService.patchUpdate(id ,userRequest);
        UserResponse userExcepted = new UserResponse(userUpdated.getId(),userUpdated.getUsername(), userUpdated.getRoles());

        verify(userRepository, times(1)).save(userUpdated);
        verifyUserResponse(userResponse, userExcepted);
    }

    @Test
    void shouldPutUpdateUser() {
        UserRequest userRequest = new UserRequest("vinicius", "teste", UserRole.ROLE_USER);
        Long id = 1L;
        User userUpdated = new User(userRequest.username(), "teste", userRequest.roles());

        when(passwordEncoder.encode(userRequest.password())).thenReturn("teste");
        when(userRepository.existsById(id)).thenReturn(true);
        when(userRepository.findById(id)).thenReturn(Optional.of(new User(userRequest.username(), "123", UserRole.ROLE_ADMIN)));
        when(userRepository.save(userUpdated)).thenReturn(userUpdated);

        UserResponse userResponse = userService.putUpdate(id ,userRequest);
        UserResponse userExcepted = new UserResponse(userUpdated.getId(),userUpdated.getUsername(), userUpdated.getRoles());

        verify(userRepository, times(1)).save(userUpdated);
        verifyUserResponse(userResponse, userExcepted);
    }

    @Test
    void shouldDeleteUser() {
        User user = createGenericUser();
        user.setId(1L);

        when(userRepository.existsById(user.getId())).thenReturn(true);

        userService.delete(user.getId());
    }

    @Test
    void shouldNotCreateUserWhenFieldsIsNull(){
        UserRequest userExpected = new UserRequest("vinicius", null, UserRole.ROLE_USER);
        UserRequest userExpected2 = new UserRequest(null, "12345", UserRole.ROLE_USER);

        assertThrows(RuntimeException.class, () -> userService.create(userExpected));
        assertThrows(RuntimeException.class, () -> userService.create(userExpected2));
    }

    @Test
    void shouldNotCreateUserWhenUsernameIsAlreadyUsed(){
        UserRequest userRequest = createGenericUserRequest();
        UserRequest userRequest2 = new UserRequest(userRequest.username(), userRequest.password(), UserRole.ROLE_ADMIN);
        User userCreated = new User(userRequest.username(), passwordEncoder.encode(userRequest.password()), userRequest.roles());
        User userCreated2 = new User(userRequest.username(), passwordEncoder.encode(userRequest.password()), UserRole.ROLE_ADMIN);
        
        when(userRepository.save(userCreated)).thenReturn(userCreated);
        when(userRepository.save(userCreated2)).thenThrow(DataIntegrityViolationException.class);

        UserResponse userResponse = userService.create(userRequest);
        UserResponse userExcepted = new UserResponse(userCreated.getId(),userCreated.getUsername(), userCreated.getRoles());
        verifyUserResponse(userResponse, userExcepted);
        assertThrows(RuntimeException.class, () -> userService.create(userRequest2));
    }

    @Test
    void shouldNotGetUserWhenIdIsNull(){
        User userExpected = createGenericUser();
        userExpected.setId(null);

        assertThrows(RuntimeException.class, () -> userService.findById(userExpected.getId()));
    }

    private User createGenericUser(){
        return new User("vinicius", "12345", UserRole.ROLE_USER);
    }

    private UserRequest createGenericUserRequest(){
        return new UserRequest("vinicius", "12345", UserRole.ROLE_USER);
    }

    private void verifyUserResponse(UserResponse expected, UserResponse actual){
        assertEquals(expected.username(), actual.username());
        assertEquals(expected.roles(), actual.roles());
    }

}
