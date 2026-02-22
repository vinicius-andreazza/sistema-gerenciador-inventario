package com.sig.sistema_gerenciador_inventario.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sig.sistema_gerenciador_inventario.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT SUM(i.quantity * i.value) FROM Item i")
    public Double getTotalValue();
    @Query("SELECT i FROM Item i WHERE i.quantity < i.minimiumQuantity")
    public Page<Item> getLowStock(Pageable pageable);
    @Query("SELECT COUNT(i) FROM Item i WHERE i.quantity < i.minimiumQuantity")
    public Long countLowStock();
    @Query("SELECT COUNT(i) FROM Item i WHERE i.status = 'ATIVO'")
    public Long getActiveItems();
}
