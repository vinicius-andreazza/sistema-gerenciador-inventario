package com.sig.sistema_gerenciador_inventario.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.sig.sistema_gerenciador_inventario.mapper.models.RawMaterialMapper;
import com.sig.sistema_gerenciador_inventario.model.ItemLocal;
import com.sig.sistema_gerenciador_inventario.model.RawMaterial;
import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.model.dto.request.RawMaterialPatchRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.RawMaterialRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.RawMaterialResponse;
import com.sig.sistema_gerenciador_inventario.model.enums.CategoryItem;
import com.sig.sistema_gerenciador_inventario.model.enums.StatusItem;
import com.sig.sistema_gerenciador_inventario.model.enums.TypeItem;
import com.sig.sistema_gerenciador_inventario.repository.ItemRepository;
import com.sig.sistema_gerenciador_inventario.repository.RawMaterialRepository;

import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
@ActiveProfiles("test")
class RawMaterialServiceTest {

    @Autowired
    private RawMaterialService rawMaterialService;

    @MockitoBean
    private ItemRepository itemRepository;

    @MockitoBean
    private RawMaterialRepository rawMaterialRepository;

    @MockitoBean
    private ItemService itemService;
    

    @Test
    void shouldCreateRawMaterial() {
        RawMaterialRequest request = buildRawMaterialRequest();
        RawMaterial rawMaterial = RawMaterialMapper.rawMaterialMap(request);

        when(itemRepository.save(rawMaterial)).thenReturn(rawMaterial);

        RawMaterialResponse response = rawMaterialService.create(request);

        verify(itemRepository, times(1)).save(rawMaterial);
        assertEquals(request.getBatch(), response.getBatch());
        assertEquals(request.getUnitValue(), response.getUnitValue());
    }

    @Test
    void shouldGetAllRawMaterials() {
        RawMaterial r1 = RawMaterialMapper.rawMaterialMap(buildRawMaterialRequest());
        r1.setItem_id(1L);

        RawMaterial r2 = RawMaterialMapper.rawMaterialMap(buildRawMaterialRequest());
        r2.setItem_id(2L);

        when(rawMaterialRepository.findAll()).thenReturn(List.of(r1, r2));

        List<RawMaterialResponse> response = rawMaterialService.findAll();

        assertEquals(2, response.size());
    }

    @Test
    void shouldGetRawMaterialById() {
        Long id = 1L;
        RawMaterial rawMaterial = RawMaterialMapper.rawMaterialMap(buildRawMaterialRequest());
        rawMaterial.setItem_id(id);

        when(rawMaterialRepository.findById(id))
                .thenReturn(Optional.of(rawMaterial));

        RawMaterialResponse response = rawMaterialService.findById(id);

        assertEquals(id, response.getId());
    }

    @Test
    void shouldPutUpdateRawMaterial() {
        Long id = 1L;
        RawMaterialRequest request = buildRawMaterialRequest();

        RawMaterial existing = RawMaterialMapper.rawMaterialMap(buildRawMaterialRequest());
        existing.setName("b");
        existing.setItem_id(id);

        RawMaterial updated = RawMaterialMapper.rawMaterialMap(buildRawMaterialRequest());
        updated.setItem_id(id);

        when(rawMaterialRepository.findById(id))
                .thenReturn(Optional.of(existing));
        when(itemRepository.save(updated))
                .thenReturn(updated);

        RawMaterialResponse response =
                rawMaterialService.putUpdate(id, request);

        verify(itemRepository, times(1)).save(updated);
        assertEquals(id, response.getId());
    }

    @Test
    void shouldPatchUpdateRawMaterial() {
        Long id = 1L;

        RawMaterialPatchRequest patchRequest =
                new RawMaterialPatchRequest(null,null,null,null,null,null,null,null,null,null,80,20.0,null);

        RawMaterial rawMaterial = RawMaterialMapper.rawMaterialMap(buildRawMaterialRequest());
        rawMaterial.setItem_id(id);
        rawMaterial.setBatch(10);
        rawMaterial.setUnitValue(5.0);

        when(rawMaterialRepository.findById(id))
                .thenReturn(Optional.of(rawMaterial));
        when(itemRepository.save(rawMaterial))
                .thenReturn(rawMaterial);

        RawMaterialResponse response =
                rawMaterialService.patchUpdate(id, patchRequest);

        verify(itemService, times(1))
                .patchItemFields(rawMaterial, patchRequest);
        verify(itemRepository, times(1))
                .save(rawMaterial);

        assertEquals(id, response.getId());
        assertEquals(80, response.getBatch());
        assertEquals(20.0, response.getUnitValue());
    }

    @Test
    void shouldDeleteRawMaterialById() {
        rawMaterialService.deleteById(1L);

        verify(itemRepository, times(1))
                .deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenRawMaterialNotFound() {
        when(rawMaterialRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> rawMaterialService.findById(1L));
    }

    private RawMaterialRequest buildRawMaterialRequest() {
        return new RawMaterialRequest(
                "Matéria-prima A",
                CategoryItem.Embalagem,
                TypeItem.MATERIA_PRIMA,
                "Descrição teste",
                100,
                10,
                "KG",
                StatusItem.ATIVO,
                new User(),
                new ItemLocal(),
                50,
                12.5,
                Set.of()
        );
    }
}
