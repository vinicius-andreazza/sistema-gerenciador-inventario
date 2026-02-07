package com.sig.sistema_gerenciador_inventario.model.dto.request;

import com.sig.sistema_gerenciador_inventario.model.ItemLocal;
import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.model.enums.CategoryItem;
import com.sig.sistema_gerenciador_inventario.model.enums.StatusItem;
import com.sig.sistema_gerenciador_inventario.model.enums.TypeItem;

import jakarta.annotation.Nullable;
import lombok.Getter;

@Getter
public class ItemPatchRequest {
    @Nullable
    private String name;

    @Nullable
    private CategoryItem category;

    @Nullable
    private TypeItem typeItem;

    @Nullable
    private String description;

    @Nullable
    private Integer quantity;

    @Nullable
    private String minimiumQuantity;

    @Nullable
    private String measure;

    @Nullable
    private StatusItem status;

    @Nullable
    private User user;

    @Nullable
    private ItemLocal itemLocal;
}
