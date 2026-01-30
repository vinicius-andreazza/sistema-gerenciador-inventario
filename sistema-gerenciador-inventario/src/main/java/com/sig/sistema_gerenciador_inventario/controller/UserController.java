package com.sig.sistema_gerenciador_inventario.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.model.dto.UserRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.UserResponse;
import com.sig.sistema_gerenciador_inventario.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }

    @GetMapping()
    public ResponseEntity<List<User>> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findAll(@PathVariable Long id){
        return userService.findById(id);
    }

    @PutMapping()
    public ResponseEntity<UserResponse> update(@RequestBody UserRequest userRequest){
        return userService.update(userRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return userService.delete(id);
    }
    
}
