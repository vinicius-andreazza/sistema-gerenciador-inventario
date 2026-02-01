package com.sig.sistema_gerenciador_inventario.model;

import java.util.Set;

import org.jspecify.annotations.NonNull;

import com.sig.sistema_gerenciador_inventario.model.enums.CategoryItem;
import com.sig.sistema_gerenciador_inventario.model.enums.StatusItem;
import com.sig.sistema_gerenciador_inventario.model.enums.TypeItem;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name="item_id")
public class RawMaterial extends Item {

    @NonNull
    private int batch;

    @NonNull
    private double unitValue;

    @NonNull
    @ManyToOne
    @JoinTable(name = "Supplier", joinColumns = @JoinColumn(name = "supplier_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Supplier> supplier;

    public RawMaterial(@NonNull Long id,@NonNull String name, @NonNull CategoryItem category, @NonNull TypeItem typeItem,
            @NonNull String description, @NonNull int quantity, @NonNull String minimiumQuantity,
            @NonNull String measure, @NonNull StatusItem status, @NonNull User user, @NonNull ItemLocal itemLocal,
            @NonNull int batch, @NonNull double unitValue, @NonNull Set<Supplier> supplier) {
        super(id, name, category, typeItem, description, quantity, minimiumQuantity, measure, status, user, itemLocal);
        this.batch = batch;
        this.unitValue = unitValue;
        this.supplier = supplier;
    }

    public RawMaterial(@NonNull String name, @NonNull CategoryItem category, @NonNull TypeItem typeItem,
            @NonNull String description, @NonNull int quantity, @NonNull String minimiumQuantity,
            @NonNull String measure, @NonNull StatusItem status, @NonNull User user, @NonNull ItemLocal itemLocal,
            @NonNull int batch, @NonNull double unitValue, @NonNull Set<Supplier> supplier) {
        super(name, category, typeItem, description, quantity, minimiumQuantity, measure, status, user, itemLocal);
        this.batch = batch;
        this.unitValue = unitValue;
        this.supplier = supplier;
    }

    public RawMaterial() {
    }

    
}
