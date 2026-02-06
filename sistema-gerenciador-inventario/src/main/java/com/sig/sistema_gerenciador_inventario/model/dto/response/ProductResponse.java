package com.sig.sistema_gerenciador_inventario.model.dto.response;

import org.jspecify.annotations.NonNull;

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
}
