package com.sig.sistema_gerenciador_inventario.model.dto.response;

import org.jspecify.annotations.NonNull;

import com.sig.sistema_gerenciador_inventario.model.ItemLocal;
import com.sig.sistema_gerenciador_inventario.model.enums.CategoryItem;
import com.sig.sistema_gerenciador_inventario.model.enums.StatusItem;

import lombok.Getter;

@Getter
public class ProductResponse extends ItemResponse {
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

    public ProductResponse(@NonNull String name, @NonNull CategoryItem category, @NonNull String description,
            @NonNull int quantity, @NonNull String minimiumQuantity, @NonNull String measure,
            @NonNull StatusItem status, @NonNull ItemLocal itemLocal, @NonNull double value, @NonNull double weight,
            @NonNull double height, @NonNull double length, @NonNull double depth) {
        super(name, category, description, quantity, minimiumQuantity, measure, status, itemLocal);
        this.value = value;
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.depth = depth;
    }

    public ProductResponse() {
    }
    
}
