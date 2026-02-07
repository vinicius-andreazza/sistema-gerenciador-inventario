package com.sig.sistema_gerenciador_inventario.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sig.sistema_gerenciador_inventario.mapper.models.UserMapper;
import com.sig.sistema_gerenciador_inventario.mapper.response.UserResponseMapper;
import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.model.dto.request.UserPatchRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.UserRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.UserResponse;
import com.sig.sistema_gerenciador_inventario.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse create(UserRequest userRequest) {
        if (usernameAlreadyExists(userRequest.username())) {
            throw new DataIntegrityViolationException("Nome de usuario já utilizado");
        }
        User userCreated = UserMapper.userMap(userRequest);

        userCreated = userRepository.save(userCreated);

        UserResponse userResponse = UserResponseMapper.userResponseMap(userCreated);
        return userResponse;
    }

    public UserResponse findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo");
        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        return UserResponseMapper.userResponseMap(user);
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(UserResponseMapper::userResponseMap).toList();
    }

    @Transactional
    public UserResponse putUpdate(Long id, UserRequest userRequest) {
        if (id == null || id<= 0) {
            throw new IllegalArgumentException("Id não pode ser nulo ou menor que 1");
        }
        if (usernameAlreadyExists(userRequest.username())) {
            throw new DataIntegrityViolationException("Nome de usuario já utilizado");
        }

        User userShouldBeUpdated = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        userShouldBeUpdated.setUsername(userRequest.username());
        userShouldBeUpdated.setPassword(passwordEncoder.encode(userRequest.password()));
        userShouldBeUpdated.setRoles(userRequest.roles());

        User userUpdated = userRepository.save(userShouldBeUpdated);

        UserResponse userResponse = UserResponseMapper.userResponseMap(userUpdated);
        return userResponse;

    }

    @Transactional
    public UserResponse patchUpdate(Long id, UserPatchRequest userRequest) {
        if (id == null || id<= 0) {
            throw new IllegalArgumentException("Id não pode ser nulo ou menor que 1");
        }
        if (usernameAlreadyExists(userRequest.username())) {
            throw new DataIntegrityViolationException("Nome de usuario já utilizado");
        }

        User userShouldBeUpdated = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        userShouldBeUpdated.setUsername(
                userRequest.username() == null || userRequest.username().isBlank() 
                        ? userShouldBeUpdated.getUsername()
                        : userRequest.username());

        userShouldBeUpdated.setPassword(
                userRequest.password() == null || userRequest.password().isBlank() 
                        ? userShouldBeUpdated.getPassword()
                        : passwordEncoder.encode(userRequest.password()));

        userShouldBeUpdated
                .setRoles(userRequest.roles() == null ? userShouldBeUpdated.getRoles() : userRequest.roles());


        User userUpdated = userRepository.save(userShouldBeUpdated);
        UserResponse userResponse = UserResponseMapper.userResponseMap(userUpdated);
        return userResponse;

    }

    @Transactional
    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private boolean usernameAlreadyExists(String username) {
        return userRepository.existsByUsername(username);
    }

}
