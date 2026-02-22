package com.sig.sistema_gerenciador_inventario.model;

import com.sig.sistema_gerenciador_inventario.model.enums.CategoryItem;
import com.sig.sistema_gerenciador_inventario.model.enums.StatusItem;
import com.sig.sistema_gerenciador_inventario.model.enums.TypeItem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "T_SGI_ITEM")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private Long item_id;

    @NotBlank
    @Column(name = "nm_item")
    private String name;

    @NotNull
    @Column(name = "ds_category")
    @Enumerated(EnumType.STRING)
    private CategoryItem category;

    @NotNull
    @Column(name = "tp_item", updatable=false)
    @Enumerated(EnumType.STRING)
    private TypeItem typeItem;

    @NotBlank
    @Column(name = "ds_item")
    private String description;

    @NotNull
    @Column(name = "nr_quantity")
    private Integer quantity;

    @NotNull
    @Column(name = "nr_minimium_quantity")
    private Integer minimiumQuantity;

    @NotBlank
    @Column(name = "ds_measure")
    private String measure;

    @NotNull
    @Column(name = "st_item")
    @Enumerated(EnumType.STRING)
    private StatusItem status;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_local")
    private ItemLocal itemLocal;

    @NotNull
    @Column(name = "vl_value")
    private Double value;

    public Item(Long item_id, @NotBlank String name, @NotNull CategoryItem category, @NotNull TypeItem typeItem,
            @NotBlank String description, @NotNull Integer quantity, @NotNull Integer minimiumQuantity,
            @NotBlank String measure, @NotNull StatusItem status, @NotNull User user, @NotNull ItemLocal itemLocal, @NotNull Double value) {
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
        this.value = value;
    }



    public Item(@NotNull String name, @NotNull CategoryItem category, @NotNull TypeItem typeItem,
            @NotNull String description, @NotNull Integer quantity, @NotNull Integer minimiumQuantity,
            @NotNull String measure, @NotNull StatusItem status, @NotNull User user, @NotNull ItemLocal itemLocal, @NotNull Double value) {
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
        this.value = value;
    }

    

    public Item(@NotBlank String name, @NotNull CategoryItem category, @NotNull TypeItem typeItem,
            @NotBlank String description, @NotNull Integer quantity, @NotNull Integer minimiumQuantity,
            @NotBlank String measure, @NotNull StatusItem status, @NotNull Double value) {
        this.name = name;
        this.category = category;
        this.typeItem = typeItem;
        this.description = description;
        this.quantity = quantity;
        this.minimiumQuantity = minimiumQuantity;
        this.measure = measure;
        this.status = status;
        this.value = value;
    }



    public Item() {
    }

    
}
