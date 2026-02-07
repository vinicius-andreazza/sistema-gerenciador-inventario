package com.sig.sistema_gerenciador_inventario.model.dto.request;



import com.sig.sistema_gerenciador_inventario.model.ItemLocal;
import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.model.enums.CategoryItem;
import com.sig.sistema_gerenciador_inventario.model.enums.StatusItem;
import com.sig.sistema_gerenciador_inventario.model.enums.TypeItem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ItemRequest {

    @NotBlank
    private String name;

    @NotNull
    private CategoryItem category;

    @NotNull
    private TypeItem typeItem;

    @NotBlank
    private String description;

    @NotNull
    private Integer quantity;

    @NotNull
    private Integer minimiumQuantity;

    @NotBlank
    private String measure;

    @NotNull
    private StatusItem status;

    @NotNull
    private User user;

    @NotNull
    private ItemLocal itemLocal;
}
