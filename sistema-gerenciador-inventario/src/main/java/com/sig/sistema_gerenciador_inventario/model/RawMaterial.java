package com.sig.sistema_gerenciador_inventario.model;

import java.util.Set;

import com.sig.sistema_gerenciador_inventario.model.enums.CategoryItem;
import com.sig.sistema_gerenciador_inventario.model.enums.StatusItem;
import com.sig.sistema_gerenciador_inventario.model.enums.TypeItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name="item_id")
public class RawMaterial extends Item {

    @NotNull
    private Integer batch;

    @NotNull
    private Double unitValue;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "Supplier_Material", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "supplier_id"))
    private Set<Supplier> supplier;

    public RawMaterial(@NotNull Long id,@NotBlank String name, @NotNull CategoryItem category, @NotNull TypeItem typeItem,
            @NotBlank String description, @NotNull Integer quantity, @NotNull Integer minimiumQuantity,
            @NotBlank String measure, @NotNull StatusItem status, @NotNull User user, @NotNull ItemLocal itemLocal,
            @NotNull Integer batch, @NotNull Double unitValue, @NotNull Set<Supplier> supplier) {
        super(id, name, category, typeItem, description, quantity, minimiumQuantity, measure, status, user, itemLocal);
        this.batch = batch;
        this.unitValue = unitValue;
        this.supplier = supplier;
    }

    public RawMaterial(@NotBlank String name, @NotNull CategoryItem category, @NotNull TypeItem typeItem,
            @NotBlank String description, @NotNull Integer quantity, @NotNull Integer minimiumQuantity,
            @NotBlank String measure, @NotNull StatusItem status, @NotNull User user, @NotNull ItemLocal itemLocal,
            @NotNull Integer batch, @NotNull Double unitValue, @NotNull Set<Supplier> supplier) {
        super(name, category, typeItem, description, quantity, minimiumQuantity, measure, status, user, itemLocal);
        this.batch = batch;
        this.unitValue = unitValue;
        this.supplier = supplier;
    }

    public RawMaterial(@NotBlank String name, @NotNull CategoryItem category, @NotNull TypeItem typeItem,
            @NotBlank String description, @NotNull Integer quantity, @NotNull Integer minimiumQuantity,
            @NotBlank String measure, @NotNull StatusItem status, @NotNull Integer batch, @NotNull Double unitValue,
            @NotNull Set<Supplier> supplier) {
        super(name, category, typeItem, description, quantity, minimiumQuantity, measure, status);
        this.batch = batch;
        this.unitValue = unitValue;
        this.supplier = supplier;
    }

    public RawMaterial() {
    }

    
}
