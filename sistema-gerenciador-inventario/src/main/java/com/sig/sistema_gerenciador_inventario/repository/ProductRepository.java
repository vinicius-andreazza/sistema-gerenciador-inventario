package com.sig.sistema_gerenciador_inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sig.sistema_gerenciador_inventario.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
