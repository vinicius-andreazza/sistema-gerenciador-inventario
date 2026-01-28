package com.sig.sistema_gerenciador_inventario.model.dto;

public record UserLoginRequest(
    String username,
    String password
) {
}
