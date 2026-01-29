package com.sig.sistema_gerenciador_inventario.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findAll(@PathVariable Long id){
        return userService.findById(id);
    }
    
}
