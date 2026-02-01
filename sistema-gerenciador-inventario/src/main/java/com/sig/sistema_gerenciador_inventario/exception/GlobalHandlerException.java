package com.sig.sistema_gerenciador_inventario.exception;

import javax.management.openmbean.InvalidKeyException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }

    @ExceptionHandler(InvalidKeyException.class)
    public ResponseEntity<?> handleInvalidKeyException(InvalidKeyException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex){
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }
}
