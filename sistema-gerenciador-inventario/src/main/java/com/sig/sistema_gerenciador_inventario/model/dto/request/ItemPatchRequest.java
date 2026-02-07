package com.sig.sistema_gerenciador_inventario.model.dto.request;

import com.sig.sistema_gerenciador_inventario.model.ItemLocal;
import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.model.enums.CategoryItem;
import com.sig.sistema_gerenciador_inventario.model.enums.StatusItem;
import com.sig.sistema_gerenciador_inventario.model.enums.TypeItem;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import lombok.Getter;

@Getter
public class ItemPatchRequest {
    @Null
    @NotEmpty
    private String name;

    @Null
    private CategoryItem category;

    @Null
    private TypeItem typeItem;

    @Null
    @NotEmpty
    private String description;

    @Null
    private Integer quantity;

    @Null
    private Integer minimiumQuantity;

    @Null
    @NotEmpty
    private String measure;

    @Null
    private StatusItem status;

    @Null
    private User user;

    @Null
    private ItemLocal itemLocal;
}
