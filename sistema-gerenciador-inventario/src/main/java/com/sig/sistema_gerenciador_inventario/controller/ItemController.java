package com.sig.sistema_gerenciador_inventario.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sig.sistema_gerenciador_inventario.service.ItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/totalValue")
    public ResponseEntity<Double> getTotalValue() {
        return ResponseEntity.ok(itemService.getTotalValue());
    }

    @GetMapping("/lowStock")
    public ResponseEntity<Long> getLowStock() {
        return ResponseEntity.ok(itemService.getLowStock());
    }
}
