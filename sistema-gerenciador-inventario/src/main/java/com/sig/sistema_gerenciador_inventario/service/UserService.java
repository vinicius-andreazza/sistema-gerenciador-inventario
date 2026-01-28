package com.sig.sistema_gerenciador_inventario.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.repository.UserRepository;
import com.sig.sistema_gerenciador_inventario.service.interfaces.Crudable;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements Crudable<User> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<User> create(User t) {
        if(t.getUsername().isEmpty()){
            throw new RuntimeException();
        }
        if(t.getPassword().isEmpty()){
            throw new RuntimeException();
        }
        t.setPassword(passwordEncoder.encode(t.getPassword()));
        User userCreated = userRepository.save(t);
        return ResponseEntity.status(201).body(userCreated);
    }

    @Override
    public ResponseEntity<User> findById(Long id) {
        if(id == null){
            throw new RuntimeException();
        }
        return ResponseEntity.ok(userRepository.findById(id).get());
    }

    @Override
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @Override
    public ResponseEntity<User> update(User t) {
        t.setPassword(passwordEncoder.encode(t.getPassword()));
        User userUpdated = userRepository.save(t);
        return ResponseEntity.ok(userUpdated);
        
    }

    @Override
    public ResponseEntity delete(User t) {
        userRepository.delete(t);
        return ResponseEntity.noContent().build();
    }
    
}
