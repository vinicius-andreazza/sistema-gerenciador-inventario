package com.sig.sistema_gerenciador_inventario.model;

import com.sig.sistema_gerenciador_inventario.model.enums.CategoryItem;
import com.sig.sistema_gerenciador_inventario.model.enums.StatusItem;
import com.sig.sistema_gerenciador_inventario.model.enums.TypeItem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item_id;

    @NotBlank
    private String name;

    @NotNull
    private CategoryItem category;

    @NotNull
    @Column(insertable=false, updatable=false)
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
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
    private ItemLocal itemLocal;

    

    public Item(Long item_id, @NotBlank String name, @NotNull CategoryItem category, @NotNull TypeItem typeItem,
            @NotBlank String description, @NotNull Integer quantity, @NotNull Integer minimiumQuantity,
            @NotBlank String measure, @NotNull StatusItem status, @NotNull User user, @NotNull ItemLocal itemLocal) {
        this.item_id = item_id;
        this.name = name;
        this.category = category;
        this.typeItem = typeItem;
        this.description = description;
        this.quantity = quantity;
        this.minimiumQuantity = minimiumQuantity;
        this.measure = measure;
        this.status = status;
        this.user = user;
        this.itemLocal = itemLocal;
    }



    public Item(@NotNull String name, @NotNull CategoryItem category, @NotNull TypeItem typeItem,
            @NotNull String description, @NotNull Integer quantity, @NotNull Integer minimiumQuantity,
            @NotNull String measure, @NotNull StatusItem status, @NotNull User user, @NotNull ItemLocal itemLocal) {
        this.name = name;
        this.category = category;
        this.typeItem = typeItem;
        this.description = description;
        this.quantity = quantity;
        this.minimiumQuantity = minimiumQuantity;
        this.measure = measure;
        this.status = status;
        this.user = user;
        this.itemLocal = itemLocal;
    }

    

    public Item(@NotBlank String name, @NotNull CategoryItem category, @NotNull TypeItem typeItem,
            @NotBlank String description, @NotNull Integer quantity, @NotNull Integer minimiumQuantity,
            @NotBlank String measure, @NotNull StatusItem status) {
        this.name = name;
        this.category = category;
        this.typeItem = typeItem;
        this.description = description;
        this.quantity = quantity;
        this.minimiumQuantity = minimiumQuantity;
        this.measure = measure;
        this.status = status;
    }



    public Item() {
    }

    
}
