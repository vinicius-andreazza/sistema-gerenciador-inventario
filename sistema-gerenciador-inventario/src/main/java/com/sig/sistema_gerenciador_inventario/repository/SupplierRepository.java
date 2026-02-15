package com.sig.sistema_gerenciador_inventario.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sig.sistema_gerenciador_inventario.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    public Page<Supplier> findAll(Pageable pageable);
}
