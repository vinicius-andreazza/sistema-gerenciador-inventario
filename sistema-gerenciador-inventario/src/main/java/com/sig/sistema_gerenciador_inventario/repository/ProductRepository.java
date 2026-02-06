package com.sig.sistema_gerenciador_inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sig.sistema_gerenciador_inventario.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
