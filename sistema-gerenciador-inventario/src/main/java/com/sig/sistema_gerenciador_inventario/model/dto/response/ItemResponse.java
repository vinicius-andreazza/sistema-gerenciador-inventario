package com.sig.sistema_gerenciador_inventario.model.dto.response;

import org.jspecify.annotations.NonNull;

import com.sig.sistema_gerenciador_inventario.model.ItemLocal;
import com.sig.sistema_gerenciador_inventario.model.enums.CategoryItem;
import com.sig.sistema_gerenciador_inventario.model.enums.StatusItem;

import lombok.Getter;

@Getter
public class ItemResponse {
    @NonNull
    private String name;

    @NonNull
    private CategoryItem category;

    @NonNull
    private String description;

    @NonNull
    private Integer quantity;

    @NonNull
    private Integer minimiumQuantity;

    @NonNull
    private String measure;

    @NonNull
    private StatusItem status;

    @NonNull
    private ItemLocal itemLocal;

    public ItemResponse(@NonNull String name, @NonNull CategoryItem category, @NonNull String description,
            @NonNull Integer quantity, @NonNull Integer minimiumQuantity, @NonNull String measure,
            @NonNull StatusItem status, @NonNull ItemLocal itemLocal) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.quantity = quantity;
        this.minimiumQuantity = minimiumQuantity;
        this.measure = measure;
        this.status = status;
        this.itemLocal = itemLocal;
    }

    public ItemResponse() {
    }

    

}
