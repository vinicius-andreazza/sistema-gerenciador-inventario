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
    private Long userId;

    @NotNull
    private Long itemLocalId;

    public ItemRequest(@NotBlank String name, @NotNull CategoryItem category, @NotNull TypeItem typeItem,
            @NotBlank String description, @NotNull Integer quantity, @NotNull Integer minimiumQuantity,
            @NotBlank String measure, @NotNull StatusItem status, @NotNull Long user, @NotNull Long itemLocal) {
        this.name = name;
        this.category = category;
        this.typeItem = typeItem;
        this.description = description;
        this.quantity = quantity;
        this.minimiumQuantity = minimiumQuantity;
        this.measure = measure;
        this.status = status;
        this.userId = user;
        this.itemLocalId = itemLocal;
    }

    public ItemRequest() {
    }

    
}
