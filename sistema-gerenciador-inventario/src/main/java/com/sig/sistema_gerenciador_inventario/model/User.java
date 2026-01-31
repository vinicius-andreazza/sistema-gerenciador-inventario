package com.sig.sistema_gerenciador_inventario.model;

import org.jspecify.annotations.NonNull;

import com.sig.sistema_gerenciador_inventario.model.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    @NonNull
    private String username;

    @NonNull
    private String password;

    @Enumerated(EnumType.STRING)
    @NonNull
    private UserRole roles;

    public User(@NonNull String username, @NonNull String password, @NonNull UserRole roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User(@NonNull String username, @NonNull String password) {
        this.username = username;
        this.password = password;
    }

    public User(@NonNull String username, @NonNull UserRole roles) {
        this.username = username;
        this.roles = roles;
    }

    public User() {
    }

    
}
