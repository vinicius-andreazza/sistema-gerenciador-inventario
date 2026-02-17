package com.sig.sistema_gerenciador_inventario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
@Table(name = "T_SGI_LOCAL")
public class ItemLocal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_local")
    private Long local_id;

    @NotBlank
    @Column(name = "nm_sector")
    private String sectorName;

    @NotNull
    @Column(name = "nr_position")
    private Integer position;

    @NotBlank
    @Column(name = "ds_shelf")
    private String shelf;

    public ItemLocal(Long local_id, @NotBlank String sectorName, @NotNull Integer position, @NotBlank String shelf) {
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
