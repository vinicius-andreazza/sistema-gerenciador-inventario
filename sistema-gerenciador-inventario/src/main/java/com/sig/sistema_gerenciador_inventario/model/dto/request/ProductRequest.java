package com.sig.sistema_gerenciador_inventario.model.dto.request;

import com.sig.sistema_gerenciador_inventario.model.ItemLocal;
import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.model.enums.CategoryItem;
import com.sig.sistema_gerenciador_inventario.model.enums.StatusItem;
import com.sig.sistema_gerenciador_inventario.model.enums.TypeItem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;

@Getter
public class ProductRequest extends ItemRequest {
    @Null
    private Double value;

    @Null
    private Double weight;

    @Null
    private Double height;

    @Null
    private Double length;

    @Null
    private Double depth;

    public ProductRequest(@NotBlank String name, @NotNull CategoryItem category, @NotNull TypeItem typeItem,
            @NotBlank String description, @NotNull Integer quantity, @NotNull Integer minimiumQuantity,
            @NotBlank String measure, @NotNull StatusItem status, @NotNull Long user, @NotNull Long itemLocal) {
        super(name, category, typeItem, description, quantity, minimiumQuantity, measure, status, user, itemLocal);
    }

    public ProductRequest(@NotBlank String name, @NotNull CategoryItem category, @NotNull TypeItem typeItem,
            @NotBlank String description, @NotNull Integer quantity, @NotNull Integer minimiumQuantity,
            @NotBlank String measure, @NotNull StatusItem status, @NotNull Long user, @NotNull Long itemLocal,
            @Null Double value, @Null Double weight, @Null Double height, @Null Double length, @Null Double depth) {
        super(name, category, typeItem, description, quantity, minimiumQuantity, measure, status, user, itemLocal);
        this.value = value;
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.depth = depth;
    }

    public ProductRequest() {
    }

    
}
