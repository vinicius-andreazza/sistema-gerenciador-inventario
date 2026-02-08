package com.sig.sistema_gerenciador_inventario.model.dto.request;

import java.util.Set;

import com.sig.sistema_gerenciador_inventario.model.ItemLocal;
import com.sig.sistema_gerenciador_inventario.model.Supplier;
import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.model.enums.CategoryItem;
import com.sig.sistema_gerenciador_inventario.model.enums.StatusItem;
import com.sig.sistema_gerenciador_inventario.model.enums.TypeItem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class RawMaterialRequest extends ItemRequest {
    @NotNull
    private Integer batch;

    @NotNull
    private Double unitValue;

    @NotNull
    private Set<Supplier> supplier;

    public RawMaterialRequest(@NotBlank String name, @NotNull CategoryItem category, @NotNull TypeItem typeItem,
            @NotBlank String description, @NotNull Integer quantity, @NotNull Integer minimiumQuantity,
            @NotBlank String measure, @NotNull StatusItem status, @NotNull User user, @NotNull ItemLocal itemLocal) {
        super(name, category, typeItem, description, quantity, minimiumQuantity, measure, status, user, itemLocal);
    }

    public RawMaterialRequest(@NotBlank String name, @NotNull CategoryItem category, @NotNull TypeItem typeItem,
            @NotBlank String description, @NotNull Integer quantity, @NotNull Integer minimiumQuantity,
            @NotBlank String measure, @NotNull StatusItem status, @NotNull User user, @NotNull ItemLocal itemLocal,
            @NotNull Integer batch, @NotNull Double unitValue, @NotNull Set<Supplier> supplier) {
        super(name, category, typeItem, description, quantity, minimiumQuantity, measure, status, user, itemLocal);
        this.batch = batch;
        this.unitValue = unitValue;
        this.supplier = supplier;
    }

    public RawMaterialRequest() {
    }

    
}
