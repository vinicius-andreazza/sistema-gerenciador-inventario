package com.sig.sistema_gerenciador_inventario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    private String sectorName;

    @NotNull
    private Integer position;

    @NotBlank
    private String shelf;

    public ItemLocal(Long local_id, @NotBlank String sectorName, @NotNull int position, @NotBlank String shelf) {
        this.local_id = local_id;
        this.sectorName = sectorName;
        this.position = position;
        this.shelf = shelf;
    }

    public ItemLocal(@NotBlank String sectorName, @NotNull int position, @NotBlank String shelf) {
        this.sectorName = sectorName;
        this.position = position;
        this.shelf = shelf;
    }

    public ItemLocal() {
    }

    
}
