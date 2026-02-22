package com.sig.sistema_gerenciador_inventario.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sig.sistema_gerenciador_inventario.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public Page<Product> findAll(Pageable pageable);
    @Query("SELECT SUM(p.quantity) FROM Product p")
    public Integer sumTotalQuantity();
}
