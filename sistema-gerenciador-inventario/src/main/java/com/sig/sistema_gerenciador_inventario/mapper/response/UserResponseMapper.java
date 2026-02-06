package com.sig.sistema_gerenciador_inventario.mapper.response;

import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.model.dto.response.UserResponse;

public interface UserResponseMapper {
    public static UserResponse userResponseMap(User u){
        return new UserResponse(u.getId(),u.getUsername(), u.getRoles());
    }
}
