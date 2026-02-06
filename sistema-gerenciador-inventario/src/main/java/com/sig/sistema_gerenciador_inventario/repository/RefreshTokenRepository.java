package com.sig.sistema_gerenciador_inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sig.sistema_gerenciador_inventario.model.RefreshToken;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    public Optional<RefreshToken> findByToken(String token);
    public Optional<RefreshToken> findByUsername(String username);
    @Modifying
    @Transactional
    @Query("DELETE FROM RefreshToken WHERE (token = :refreshtoken)")
    public void deleteByToken(@Param("refreshtoken") String token);
}
