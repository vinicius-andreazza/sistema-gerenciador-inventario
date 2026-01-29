package com.sig.sistema_gerenciador_inventario.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.model.enums.UserRole;
import com.sig.sistema_gerenciador_inventario.repository.UserRepository;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {
    @MockitoBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Test
    void shouldCreateUser(){
        User userExpected = createGenericUser();
        when(userRepository.save(userExpected)).thenReturn(userExpected);

        User userActual = userService.create(userExpected).getBody();

        verify(userRepository, times(1)).save(userExpected);
        verifyUser(userActual, userExpected);
    }

    @Test
    void shouldGetUser(){
        User userExpected = createGenericUser();
        userExpected.setId(1L);
        when(userRepository.findById(userExpected.getId())).thenReturn(Optional.of(userExpected));

        User userActual = userService.findById(userExpected.getId()).getBody();

        verify(userRepository, times(1)).findById(userExpected.getId());
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
        User user = createGenericUser();
        user.setId(1L);

        userService.update(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void shouldDeleteUser() {
        User user = createGenericUser();
        user.setId(1L);

        userService.delete(user);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void shouldNotCreateUserWhenFieldsIsNull(){
        User userExpected = createGenericUser();
        User userExpected2 = createGenericUser();
        userExpected.setUsername(null);
        userExpected2.setPassword(null);

        assertThrows(RuntimeException.class, () -> userService.create(userExpected));
        assertThrows(RuntimeException.class, () -> userService.create(userExpected2));
    }

    @Test
    void shouldNotCreateUserWhenUsernameIsAlreadyUsed(){
        User userExpected = createGenericUser();
        User userExpected2 = createGenericUser();
        userExpected2.setRoles(UserRole.ROLE_ADMIN);
        when(userRepository.save(userExpected)).thenReturn(userExpected);
        when(userRepository.save(userExpected2)).thenThrow(DataIntegrityViolationException.class);

        User userActual = userService.create(userExpected).getBody();

        verify(userRepository, times(1)).save(userExpected);
        verifyUser(userActual, userExpected);
        assertThrows(RuntimeException.class, () -> userService.create(userExpected2));
    }

    @Test
    void shouldNotGetUserWhenIdIsNull(){
        User userExpected = createGenericUser();
        userExpected.setId(null);

        assertThrows(RuntimeException.class, () -> userService.findById(userExpected.getId()));
    }

    @Test
    void shouldNotUpdateUserWhenFieldsIsNull(){
        User userExpected = createGenericUser();
        User userExpected2 = createGenericUser();
        userExpected.setUsername(null);
        userExpected2.setPassword(null);

        assertThrows(RuntimeException.class, () -> userService.update(userExpected));
        assertThrows(RuntimeException.class, () -> userService.update(userExpected2));
    }


    private User createGenericUser(){
        return new User("vinicius", "12345", UserRole.ROLE_USER);
    }

    private void verifyUser(User expected, User actual){
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getRoles(), actual.getRoles());
    }
}
