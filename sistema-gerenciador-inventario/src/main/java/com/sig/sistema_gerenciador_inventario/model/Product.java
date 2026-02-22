package com.sig.sistema_gerenciador_inventario.model;

import com.sig.sistema_gerenciador_inventario.model.enums.CategoryItem;
import com.sig.sistema_gerenciador_inventario.model.enums.StatusItem;
import com.sig.sistema_gerenciador_inventario.model.enums.TypeItem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name="id_item")
@Table(name = "T_SGI_PRODUCT")
public class Product extends Item {
    
    @NotNull
    @Column(name = "vl_weight")
    private Double weight;

    @NotNull
    @Column(name = "vl_height")
    private Double height;

    @NotNull
    @Column(name = "vl_length")
    private Double length;

    @NotNull
    @Column(name = "vl_depth")
    private Double depth;

    public Product(Long item_id, @NotBlank String name, @NotNull CategoryItem category, @NotNull TypeItem typeItem,
            @NotBlank String description, @NotNull Integer quantity, @NotNull Integer minimiumQuantity,
            @NotBlank String measure, @NotNull StatusItem status, @NotNull User user, @NotNull ItemLocal itemLocal,
            @NotNull Double value, @NotNull Double weight, @NotNull Double height, @NotNull Double length,
            @NotNull Double depth) {
        super(item_id, name, category, typeItem, description, quantity, minimiumQuantity, measure, status, user,
                itemLocal, value);
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
        super(name, category, typeItem, description, quantity, minimiumQuantity, measure, status, user, itemLocal, value);
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.depth = depth;
    }

    

    public Product(@NotBlank String name, @NotNull CategoryItem category, @NotNull TypeItem typeItem,
            @NotBlank String description, @NotNull Integer quantity, @NotNull Integer minimiumQuantity,
            @NotBlank String measure, @NotNull StatusItem status, @NotNull Double value, @NotNull Double weight,
            @NotNull Double height, @NotNull Double length, @NotNull Double depth) {
        super(name, category, typeItem, description, quantity, minimiumQuantity, measure, status, value);
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.depth = depth;
    }

    public Product() {
    }
    
}
