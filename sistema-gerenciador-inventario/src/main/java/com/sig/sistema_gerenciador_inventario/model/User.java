package com.sig.sistema_gerenciador_inventario.model;

import com.sig.sistema_gerenciador_inventario.model.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull
    private UserRole roles;

    public User(Long id,@NotBlank String username, @NotBlank String password, @NotNull UserRole roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    
    public User(@NotBlank String username, @NotBlank String password, @NotNull UserRole roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User(@NotBlank String username, @NotBlank String password) {
        this.username = username;
        this.password = password;
    }

    public User(@NotBlank String username, @NotNull UserRole roles) {
        this.username = username;
        this.roles = roles;
    }

    public User() {
    }

    
}
