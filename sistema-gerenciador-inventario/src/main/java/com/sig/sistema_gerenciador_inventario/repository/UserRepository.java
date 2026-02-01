package com.sig.sistema_gerenciador_inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sig.sistema_gerenciador_inventario.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
    public boolean existsByUsername(String username);
}
