package com.sig.sistema_gerenciador_inventario.mapper.models;

import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.model.dto.request.UserRequest;

public interface UserMapper {
    public static User userMap(UserRequest request){
        return new User(request.username(), request.password(), request.roles());
    }
}
