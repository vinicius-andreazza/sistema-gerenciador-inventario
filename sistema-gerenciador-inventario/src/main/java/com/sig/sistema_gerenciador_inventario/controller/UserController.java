package com.sig.sistema_gerenciador_inventario.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sig.sistema_gerenciador_inventario.model.dto.request.UserCreateRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.UserUpdateRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.UserResponse;
import com.sig.sistema_gerenciador_inventario.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<UserResponse> create(@RequestBody UserCreateRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userRequest)) ;
    }

    @GetMapping()
    public ResponseEntity<List<UserResponse>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findAll(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping()
    public ResponseEntity<UserResponse> update(@RequestBody UserUpdateRequest userRequest){
        return ResponseEntity.ok(userService.update(userRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
