package com.sig.sistema_gerenciador_inventario.model;

import java.util.Set;

import com.sig.sistema_gerenciador_inventario.model.enums.CategoryItem;
import com.sig.sistema_gerenciador_inventario.model.enums.StatusItem;
import com.sig.sistema_gerenciador_inventario.model.enums.TypeItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "T_SGI_RAW_MATERIAL")
public class RawMaterial extends Item {

    @NotNull
    @Column(name = "nr_batch")
    private Integer batch;

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "T_SUPPLIER_MATERIAL", joinColumns = @JoinColumn(name = "id_raw_material"), inverseJoinColumns = @JoinColumn(name = "id_supplier"))
    private Set<Supplier> supplier;

    public RawMaterial(@NotNull Long id,@NotBlank String name, @NotNull CategoryItem category, @NotNull TypeItem typeItem,
            @NotBlank String description, @NotNull Integer quantity, @NotNull Integer minimiumQuantity,
            @NotBlank String measure, @NotNull StatusItem status, @NotNull User user, @NotNull ItemLocal itemLocal,
            @NotNull Integer batch, @NotNull Double value, @NotNull Set<Supplier> supplier) {
        super(id, name, category, typeItem, description, quantity, minimiumQuantity, measure, status, user, itemLocal, value);
        this.batch = batch;
        this.supplier = supplier;
    }

    public RawMaterial(@NotBlank String name, @NotNull CategoryItem category, @NotNull TypeItem typeItem,
            @NotBlank String description, @NotNull Integer quantity, @NotNull Integer minimiumQuantity,
            @NotBlank String measure, @NotNull StatusItem status, @NotNull User user, @NotNull ItemLocal itemLocal,
            @NotNull Integer batch, @NotNull Double value, @NotNull Set<Supplier> supplier) {
        super(name, category, typeItem, description, quantity, minimiumQuantity, measure, status, user, itemLocal, value);
        this.batch = batch;
        this.supplier = supplier;
    }

    public RawMaterial(@NotBlank String name, @NotNull CategoryItem category, @NotNull TypeItem typeItem,
            @NotBlank String description, @NotNull Integer quantity, @NotNull Integer minimiumQuantity,
            @NotBlank String measure, @NotNull StatusItem status, @NotNull Integer batch, @NotNull Double value,
            @NotNull Set<Supplier> supplier) {
        super(name, category, typeItem, description, quantity, minimiumQuantity, measure, status, value);
        this.batch = batch;
        this.supplier = supplier;
    }

    public RawMaterial() {
    }

    
}
