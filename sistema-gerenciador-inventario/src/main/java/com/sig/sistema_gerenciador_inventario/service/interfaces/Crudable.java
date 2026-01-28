package com.sig.sistema_gerenciador_inventario.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface Crudable<T> {
    public ResponseEntity<T> create(T t);
    public ResponseEntity<T> findById(Long id);
    public ResponseEntity<List<T>> findAll();
    public ResponseEntity<T> update(T t);
    public ResponseEntity delete(T t);
}
