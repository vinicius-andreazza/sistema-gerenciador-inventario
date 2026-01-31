package com.sig.sistema_gerenciador_inventario.model;

import java.util.Set;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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

    @NonNull
    private String name;

    @Nullable
    private String phone;

    @NonNull
    private String email;

    @Nullable
    @ManyToMany(mappedBy = "supplier")
    private Set<RawMaterial> rawMaterial;

    public Supplier(Long supplier_id, @NonNull String name, @Nullable String phone, @NonNull String email,
            @Nullable Set<RawMaterial> rawMaterial) {
        this.supplier_id = supplier_id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.rawMaterial = rawMaterial;
    }

    public Supplier(@NonNull String name, @Nullable String phone, @NonNull String email,
            @Nullable Set<RawMaterial> rawMaterial) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.rawMaterial = rawMaterial;
    }

    public Supplier(@NonNull String name, @NonNull String email) {
        this.name = name;
        this.email = email;
    }

    public Supplier() {
    }

    
}
