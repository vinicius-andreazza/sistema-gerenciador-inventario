package com.sig.sistema_gerenciador_inventario.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sig.sistema_gerenciador_inventario.model.ItemLocal;

@Repository
public interface ItemLocalRepository extends JpaRepository<ItemLocal, Long> {
    public Page<ItemLocal> findAll(Pageable pageable);

    @Query("SELECT i FROM ItemLocal i WHERE " +
            "LOWER(i.sectorName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(i.shelf) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<ItemLocal> findBySetorOrPacote(String searchTerm, Pageable pageable);
}
