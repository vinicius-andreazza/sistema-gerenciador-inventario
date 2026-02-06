package com.sig.sistema_gerenciador_inventario.model.dto.request;

import org.jspecify.annotations.NonNull;

import com.sig.sistema_gerenciador_inventario.model.ItemLocal;
import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.model.enums.CategoryItem;
import com.sig.sistema_gerenciador_inventario.model.enums.StatusItem;
import com.sig.sistema_gerenciador_inventario.model.enums.TypeItem;

import lombok.Getter;

@Getter
public class ItemRequest {
    @NonNull
    private String name;

    @NonNull
    private CategoryItem category;

    @NonNull
    private TypeItem typeItem;

    @NonNull
    private String description;

    @NonNull
    private int quantity;

    @NonNull
    private String minimiumQuantity;

    @NonNull
    private String measure;

    @NonNull
    private StatusItem status;

    @NonNull
    private User user;

    @NonNull
    private ItemLocal itemLocal;
}
