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
    private double value;

    @NonNull
    private double weight;

    @NonNull
    private double height;

    @NonNull
    private double length;

    @NonNull
    private double depth;

    public Product(Long item_id, @NonNull String name, @NonNull CategoryItem category, @NonNull TypeItem typeItem,
            @NonNull String description, @NonNull int quantity, @NonNull String minimiumQuantity,
            @NonNull String measure, @NonNull StatusItem status, @NonNull User user, @NonNull ItemLocal itemLocal,
            @NonNull double value, @NonNull double weight, @NonNull double height, @NonNull double length,
            @NonNull double depth) {
        super(item_id, name, category, typeItem, description, quantity, minimiumQuantity, measure, status, user,
                itemLocal);
        this.value = value;
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.depth = depth;
    }

    public Product(@NonNull String name, @NonNull CategoryItem category, @NonNull TypeItem typeItem,
            @NonNull String description, @NonNull int quantity, @NonNull String minimiumQuantity,
            @NonNull String measure, @NonNull StatusItem status, @NonNull User user, @NonNull ItemLocal itemLocal,
            @NonNull double value, @NonNull double weight, @NonNull double height, @NonNull double length,
            @NonNull double depth) {
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
