package com.sig.sistema_gerenciador_inventario.model;

import org.jspecify.annotations.NonNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
public class ItemLocal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long local_id;

    @NonNull
    private String sectorName;

    @NonNull
    private Integer position;

    @NonNull
    private String shelf;

    public ItemLocal(Long local_id, @NonNull String sectorName, @NonNull int position, @NonNull String shelf) {
        this.local_id = local_id;
        this.sectorName = sectorName;
        this.position = position;
        this.shelf = shelf;
    }

    public ItemLocal(@NonNull String sectorName, @NonNull int position, @NonNull String shelf) {
        this.sectorName = sectorName;
        this.position = position;
        this.shelf = shelf;
    }

    public ItemLocal() {
    }

    
}
