package com.sig.sistema_gerenciador_inventario.service;

import org.springframework.stereotype.Service;

import com.sig.sistema_gerenciador_inventario.model.Item;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ItemPatchRequest;
import com.sig.sistema_gerenciador_inventario.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Double getTotalValue(){
        return itemRepository.getTotalValue();
    }

    public Long getLowStock(){
        return itemRepository.getLowStock();
    }
    
    public Long getActiveItems(){
        return itemRepository.getActiveItems();
    }

    public void patchItemFields(Item item, ItemPatchRequest request) {

    item.setName(
        request.getName() != null ? request.getName() : item.getName()
    );

    item.setCategory(
        request.getCategory() != null ? request.getCategory() : item.getCategory()
    );

    item.setTypeItem(
        request.getTypeItem() != null ? request.getTypeItem() : item.getTypeItem()
    );

    item.setDescription(
        request.getDescription() != null ? request.getDescription() : item.getDescription()
    );

    item.setQuantity(
        request.getQuantity() != null ? request.getQuantity() : item.getQuantity()
    );

    item.setMinimiumQuantity(
        request.getMinimiumQuantity() != null
            ? request.getMinimiumQuantity()
            : item.getMinimiumQuantity()
    );

    item.setMeasure(
        request.getMeasure() != null ? request.getMeasure() : item.getMeasure()
    );

    item.setStatus(
        request.getStatus() != null ? request.getStatus() : item.getStatus()
    );

    item.setUser(
        request.getUser() != null ? request.getUser() : item.getUser()
    );

    item.setItemLocal(
        request.getItemLocal() != null ? request.getItemLocal() : item.getItemLocal()
    );
}
}
