package com.sig.sistema_gerenciador_inventario.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Answers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.model.dto.UserRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.UserResponse;
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

        when(userRepository.save(userCreated)).thenReturn(userCreated);

        UserResponse userResponse = userService.create(userRequest).getBody();
        UserResponse userExcepted = new UserResponse(userCreated.getUsername(), userCreated.getRoles());

        verifyUserResponse(userResponse, userExcepted);
    }

    @Test
    void shouldGetUser(){
        User userExpected = createGenericUser();
        Long id = 1L;
        when(userRepository.findById(1L)).thenReturn(Optional.of(userExpected));

        User userActual = userService.findById(id).getBody();

        verify(userRepository, times(1)).findById(id);
        verifyUser(userActual, userExpected);
    }

    @Test
    void shouldGetAllUser(){
        User userExpected = createGenericUser();
        User userExpected2 = new User("bernardo", "123", UserRole.ROLE_USER);
        List<User> usersExpecteds = new ArrayList<>();
        usersExpecteds.add(userExpected);
        usersExpecteds.add(userExpected2);
        when(userRepository.findAll()).thenReturn(usersExpecteds);

        List<User> userActuals = userService.findAll().getBody();

        verify(userRepository, times(1)).findAll();
        for(int i=0;i<usersExpecteds.size();i++){
            verifyUser(userActuals.get(i), usersExpecteds.get(i));
        }
    }

    @Test
    void shouldUpdateUser() {
        UserRequest userRequest = createGenericUserRequest();
        User userUpdated = new User(userRequest.username(), "teste", userRequest.roles());

        when(passwordEncoder.encode(userRequest.password())).thenReturn("teste");
        when(userRepository.findByUsername(userRequest.username())).thenReturn(new User(userRequest.username(), "123", UserRole.ROLE_ADMIN));
        when(userRepository.save(userUpdated)).thenReturn(userUpdated);

        UserResponse userResponse = userService.update(userRequest).getBody();
        UserResponse userExcepted = new UserResponse(userUpdated.getUsername(), userUpdated.getRoles());

        verify(userRepository, times(1)).save(userUpdated);
        verifyUserResponse(userResponse, userExcepted);
    }

    @Test
    void shouldDeleteUser() {
        User user = createGenericUser();
        user.setId(1L);

        userService.delete(user.getId());

        verify(userRepository, times(0)).deleteById(user.getId());
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

        UserResponse userResponse = userService.create(userRequest).getBody();
        UserResponse userExcepted = new UserResponse(userCreated.getUsername(), userCreated.getRoles());
        verifyUserResponse(userResponse, userExcepted);
        assertThrows(RuntimeException.class, () -> userService.create(userRequest2));
    }

    @Test
    void shouldNotGetUserWhenIdIsNull(){
        User userExpected = createGenericUser();
        userExpected.setId(null);

        assertThrows(RuntimeException.class, () -> userService.findById(userExpected.getId()));
    }

    @Test
    void shouldNotUpdateUserWhenFieldsIsNull(){
        UserRequest userExpected = new UserRequest("vinicius", null, UserRole.ROLE_USER);
        UserRequest userExpected2 = new UserRequest(null, "12345", UserRole.ROLE_USER);

        assertThrows(RuntimeException.class, () -> userService.update(userExpected));
        assertThrows(RuntimeException.class, () -> userService.update(userExpected2));
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

    private void verifyUser(User expected, User actual){
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getRoles(), actual.getRoles());
    }
}
