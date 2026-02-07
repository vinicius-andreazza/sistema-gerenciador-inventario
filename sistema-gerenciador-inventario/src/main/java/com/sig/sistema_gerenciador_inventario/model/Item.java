package com.sig.sistema_gerenciador_inventario.model;

import org.jspecify.annotations.NonNull;

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

    @NonNull
    private String name;

    @NonNull
    private CategoryItem category;

    @NonNull
    @Column(insertable=false, updatable=false)
    private TypeItem typeItem;

    @NonNull
    private String description;

    @NonNull
    private Integer quantity;

    @NonNull
    private String minimiumQuantity;

    @NonNull
    private String measure;

    @NonNull
    private StatusItem status;

    @NonNull
    @ManyToOne
    private User user;

    @NonNull
    @ManyToOne
    private ItemLocal itemLocal;

    

    public Item(Long item_id, @NonNull String name, @NonNull CategoryItem category, @NonNull TypeItem typeItem,
            @NonNull String description, @NonNull Integer quantity, @NonNull String minimiumQuantity,
            @NonNull String measure, @NonNull StatusItem status, @NonNull User user, @NonNull ItemLocal itemLocal) {
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



    public Item(@NonNull String name, @NonNull CategoryItem category, @NonNull TypeItem typeItem,
            @NonNull String description, @NonNull Integer quantity, @NonNull String minimiumQuantity,
            @NonNull String measure, @NonNull StatusItem status, @NonNull User user, @NonNull ItemLocal itemLocal) {
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



    public Item() {
    }

    
}
