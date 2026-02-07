package com.sig.sistema_gerenciador_inventario.model;

import com.sig.sistema_gerenciador_inventario.model.enums.CategoryItem;
import com.sig.sistema_gerenciador_inventario.model.enums.StatusItem;
import com.sig.sistema_gerenciador_inventario.model.enums.TypeItem;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name="item_id")
public class Product extends Item {
    
    @NotNull
    private Double value;

    @NotNull
    private Double weight;

    @NotNull
    private Double height;

    @NotNull
    private Double length;

    @NotNull
    private Double depth;

    public Product(Long item_id, @NotBlank String name, @NotNull CategoryItem category, @NotNull TypeItem typeItem,
            @NotBlank String description, @NotNull Integer quantity, @NotNull Integer minimiumQuantity,
            @NotBlank String measure, @NotNull StatusItem status, @NotNull User user, @NotNull ItemLocal itemLocal,
            @NotNull Double value, @NotNull Double weight, @NotNull Double height, @NotNull Double length,
            @NotNull Double depth) {
        super(item_id, name, category, typeItem, description, quantity, minimiumQuantity, measure, status, user,
                itemLocal);
        this.value = value;
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.depth = depth;
    }

    public Product(@NotNull String name, @NotNull CategoryItem category, @NotNull TypeItem typeItem,
            @NotNull String description, @NotNull Integer quantity, @NotNull Integer minimiumQuantity,
            @NotNull String measure, @NotNull StatusItem status, @NotNull User user, @NotNull ItemLocal itemLocal,
            @NotNull Double value, @NotNull Double weight, @NotNull Double height, @NotNull Double length,
            @NotNull Double depth) {
        super(name, category, typeItem, description, quantity, minimiumQuantity, measure, status, user, itemLocal);
        this.value = value;
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.depth = depth;
    }

    public Product() {
    }
    
}
