package com.sig.sistema_gerenciador_inventario.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.model.dto.UserRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.UserResponse;
import com.sig.sistema_gerenciador_inventario.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<UserResponse> create(UserRequest userRequest) {
        if(userRequest.username().isEmpty()){
            throw new RuntimeException();
        }
        if(userRequest.password().isEmpty()){
            throw new RuntimeException();
        }
        User userCreated = new User(userRequest.username(), passwordEncoder.encode(userRequest.password()), userRequest.roles());
        UserResponse userResponse = null;
        try {
            User user = userRepository.save(userCreated);
            userResponse = new UserResponse(user.getUsername(), user.getRoles());
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro de integridade de dados.", e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    public ResponseEntity<User> findById(Long id) {
        if (id == null) {
            throw new RuntimeException();
        }
        return ResponseEntity.ok(userRepository.findById(id).get());
    }

    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity<UserResponse> update(UserRequest userRequest) {
        if(userRequest.username().isBlank()){
            throw new RuntimeException();
        }
        if(userRequest.password().isBlank()){
            throw new RuntimeException();
        }
        User userShouldBeUpdated = userRepository.findByUsername(userRequest.username());
        userShouldBeUpdated.setUsername(userRequest.username());
        userShouldBeUpdated.setPassword(passwordEncoder.encode(userRequest.password()));
        userShouldBeUpdated.setRoles(userRequest.roles());
        UserResponse userResponse;
        try {
            User userUpdated = userRepository.save(userShouldBeUpdated);
            userResponse = new UserResponse(userUpdated.getUsername(), userUpdated.getRoles());
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro de integridade de dados.", e);
        }
        return ResponseEntity.ok(userResponse);

    }

    @Transactional
    public ResponseEntity delete(Long id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }
        
        return ResponseEntity.noContent().build();
    }

}
