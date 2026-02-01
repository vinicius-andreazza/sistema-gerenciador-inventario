package com.sig.sistema_gerenciador_inventario.exception;

public class ExpiredRefreshTokenException extends RuntimeException {
    public ExpiredRefreshTokenException(String message){
        super(message);
    }
}
