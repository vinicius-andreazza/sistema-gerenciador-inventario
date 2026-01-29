package com.sig.sistema_gerenciador_inventario.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<User> create(User user) {
        if(user.getUsername().isEmpty()){
            throw new RuntimeException();
        }
        if(user.getPassword().isEmpty()){
            throw new RuntimeException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userCreated = null;
        try {
            userCreated = userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro de integridade de dados.", e);
        }
        return ResponseEntity.status(201).body(userCreated);
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

    public ResponseEntity<User> update(User t) {
        if (t.getUsername().isEmpty()) {
            throw new RuntimeException();
        }
        if (t.getPassword().isEmpty()) {
            throw new RuntimeException();
        }
        t.setPassword(passwordEncoder.encode(t.getPassword()));
        User userUpdated = userRepository.save(t);
        return ResponseEntity.ok(userUpdated);

    }

    public ResponseEntity delete(User t) {
        userRepository.delete(t);
        return ResponseEntity.noContent().build();
    }

}
