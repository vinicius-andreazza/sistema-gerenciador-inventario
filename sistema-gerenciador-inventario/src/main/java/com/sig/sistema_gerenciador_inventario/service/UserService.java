package com.sig.sistema_gerenciador_inventario.service;

import java.util.List;

import javax.management.openmbean.InvalidKeyException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.model.dto.request.UserCreateRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.UserUpdateRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.UserResponse;
import com.sig.sistema_gerenciador_inventario.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<UserResponse> create(UserCreateRequest userRequest) {
        if(userRequest.username().isBlank()){
            throw new IllegalArgumentException("Nome de usuario vazio");
        }
        if(userRequest.password().isBlank()){
            throw new IllegalArgumentException("Senha vazia");
        }
        User userCreated = new User(userRequest.username(), passwordEncoder.encode(userRequest.password()), userRequest.roles());
        UserResponse userResponse = null;
        try {
            User user = userRepository.save(userCreated);
            userResponse = new UserResponse(user.getUsername(), user.getRoles());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Já existe um usuario com esse nome");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    public ResponseEntity<User> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo");
        }
        return ResponseEntity.ok(userRepository.findById(id).get());
    }

    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity<UserResponse> update(UserUpdateRequest userRequest) {
        User userShouldBeUpdated = userRepository.findByUsername(userRequest.username());
        userShouldBeUpdated.setUsername(userRequest.username().isBlank() ? userShouldBeUpdated.getUsername() : userRequest.username());
        userShouldBeUpdated.setPassword(userRequest.password().isBlank() ? userShouldBeUpdated.getPassword() : passwordEncoder.encode(userRequest.password()));
        userShouldBeUpdated.setRoles(userRequest.roles() == null ? userShouldBeUpdated.getRoles() : userRequest.roles());
        UserResponse userResponse;
        try {
            User userUpdated = userRepository.save(userShouldBeUpdated);
            userResponse = new UserResponse(userUpdated.getUsername(), userUpdated.getRoles());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Já existe um usuario com esse nome");
        }
        return ResponseEntity.ok(userResponse);

    }

    @Transactional
    public ResponseEntity<?> delete(Long id) {
        if(!userRepository.existsById(id)){
            throw new InvalidKeyException("Não existe um usuario com esse ID");
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
