package com.sig.sistema_gerenciador_inventario.model;

import org.jspecify.annotations.NonNull;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name="item_id")
public class Product extends Item {
    
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
