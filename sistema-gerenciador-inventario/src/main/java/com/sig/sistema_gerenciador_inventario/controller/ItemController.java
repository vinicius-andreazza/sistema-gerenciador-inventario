package com.sig.sistema_gerenciador_inventario.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sig.sistema_gerenciador_inventario.model.dto.response.ItemLowStockResponse;
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

    @GetMapping("/quantityInLowStock")
    public ResponseEntity<Long> getQuantityInLowStock() {
        return ResponseEntity.ok(itemService.getQuantityInLowStock());
    }

    @GetMapping("/lowStock")
    public ResponseEntity<Page<ItemLowStockResponse>> getLowStock(Pageable pageable) {
        return ResponseEntity.ok(itemService.getLowStock(pageable));
    }

    @GetMapping("/activeItems")
    public ResponseEntity<Long> getActiveItems() {
        return ResponseEntity.ok(itemService.getActiveItems());
    }
}
