package com.sig.sistema_gerenciador_inventario.model.dto.response;

import java.util.Set;

import org.jspecify.annotations.NonNull;

import com.sig.sistema_gerenciador_inventario.model.ItemLocal;
import com.sig.sistema_gerenciador_inventario.model.Supplier;
import com.sig.sistema_gerenciador_inventario.model.enums.CategoryItem;
import com.sig.sistema_gerenciador_inventario.model.enums.StatusItem;

import lombok.Getter;

@Getter
public class RawMaterialResponse extends ItemResponse {
    @NonNull
    private int batch;

    @NonNull
    private double unitValue;

    @NonNull
    private Set<Supplier> supplier;

    public RawMaterialResponse(@NonNull String name, @NonNull CategoryItem category, @NonNull String description,
            @NonNull int quantity, @NonNull Integer minimiumQuantity, @NonNull String measure,
            @NonNull StatusItem status, @NonNull ItemLocal itemLocal, @NonNull int batch, @NonNull double unitValue,
            @NonNull Set<Supplier> supplier) {
        super(name, category, description, quantity, minimiumQuantity, measure, status, itemLocal);
        this.batch = batch;
        this.unitValue = unitValue;
        this.supplier = supplier;
    }

    public RawMaterialResponse() {
    }

}
