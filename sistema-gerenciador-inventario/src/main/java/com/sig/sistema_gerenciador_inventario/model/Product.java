package com.sig.sistema_gerenciador_inventario.model;

import org.jspecify.annotations.NonNull;

import com.sig.sistema_gerenciador_inventario.model.enums.CategoryItem;
import com.sig.sistema_gerenciador_inventario.model.enums.StatusItem;
import com.sig.sistema_gerenciador_inventario.model.enums.TypeItem;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name="item_id")
public class Product extends Item {
    
    @NonNull
    private Double value;

    @NonNull
    private Double weight;

    @NonNull
    private Double height;

    @NonNull
    private Double length;

    @NonNull
    private Double depth;

    public Product(Long item_id, @NonNull String name, @NonNull CategoryItem category, @NonNull TypeItem typeItem,
            @NonNull String description, @NonNull Integer quantity, @NonNull String minimiumQuantity,
            @NonNull String measure, @NonNull StatusItem status, @NonNull User user, @NonNull ItemLocal itemLocal,
            @NonNull Double value, @NonNull Double weight, @NonNull Double height, @NonNull Double length,
            @NonNull Double depth) {
        super(item_id, name, category, typeItem, description, quantity, minimiumQuantity, measure, status, user,
                itemLocal);
        this.value = value;
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.depth = depth;
    }

    public Product(@NonNull String name, @NonNull CategoryItem category, @NonNull TypeItem typeItem,
            @NonNull String description, @NonNull Integer quantity, @NonNull String minimiumQuantity,
            @NonNull String measure, @NonNull StatusItem status, @NonNull User user, @NonNull ItemLocal itemLocal,
            @NonNull Double value, @NonNull Double weight, @NonNull Double height, @NonNull Double length,
            @NonNull Double depth) {
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
