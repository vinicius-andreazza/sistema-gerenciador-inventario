package com.sig.sistema_gerenciador_inventario.model;

import java.util.Set;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplier_id;

    @NotBlank
    private String name;

    @Pattern(regexp = "^\\(\\d{2}\\)\\s9\\d{4}-\\d{4}$", message = "Celular inválido")
    private String phone;

    @NotBlank
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "supplier")
    private Set<RawMaterial> rawMaterial;

    public Supplier(Long supplier_id, @NotBlank String name, @Null String phone, @NotBlank String email,
            @Null Set<RawMaterial> rawMaterial) {
        this.supplier_id = supplier_id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.rawMaterial = rawMaterial;
    }

    public Supplier(@NotNull String name, @Null String phone, @NotNull String email,
            @Null Set<RawMaterial> rawMaterial) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.rawMaterial = rawMaterial;
    }

    public Supplier(@NotNull String name, @NotNull String email) {
        this.name = name;
        this.email = email;
    }

    public Supplier() {
    }

    
}
