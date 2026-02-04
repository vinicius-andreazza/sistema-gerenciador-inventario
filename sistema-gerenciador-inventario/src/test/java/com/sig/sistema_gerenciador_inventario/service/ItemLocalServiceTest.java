package com.sig.sistema_gerenciador_inventario.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.sig.sistema_gerenciador_inventario.model.ItemLocal;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ItemLocalRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ItemLocalPatchRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.ItemLocalResponse;
import com.sig.sistema_gerenciador_inventario.repository.ItemLocalRepository;

import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
@ActiveProfiles("test")
public class ItemLocalServiceTest {

    @MockitoBean
    private ItemLocalRepository itemLocalRepository;

    @Autowired
    private ItemLocalService itemLocalService;

    @Test
    void shouldCreateItemLocal(){
        ItemLocalRequest itemLocalrequest = createGenericItemLocalCreationRequest();
        ItemLocal itemLocalCreated = new ItemLocal(itemLocalrequest.sectorName(), itemLocalrequest.position(), itemLocalrequest.shelf());

        when(itemLocalRepository.save(itemLocalCreated)).thenReturn(itemLocalCreated);

        ItemLocalResponse itemResponse = itemLocalService.create(itemLocalrequest);
        ItemLocalResponse itemExpected = new ItemLocalResponse(itemLocalCreated.getLocal_id(),itemLocalCreated.getSectorName(), itemLocalCreated.getPosition(), itemLocalCreated.getShelf());

        verifyItemLocalResponse(itemResponse, itemExpected);
    }

    @Test
    void shouldGetItemLocal(){
        ItemLocal itemLocal = createGenericItemLocal();
        ItemLocalResponse itemLocalResponseExpected = new ItemLocalResponse(itemLocal.getLocal_id(),itemLocal.getSectorName(), itemLocal.getPosition(), itemLocal.getShelf());
        Long id = 1L;
        when(itemLocalRepository.findById(1L)).thenReturn(Optional.of(itemLocal));

        ItemLocalResponse itemLocalResponse = itemLocalService.findById(id);

        verify(itemLocalRepository, times(1)).findById(id);
        verifyItemLocalResponse(itemLocalResponse, itemLocalResponseExpected);
    }

    @Test
    void shouldGetAllItemLocal() throws Exception{
        ItemLocal itemLocalExpected = createGenericItemLocal();
        ItemLocal itemLocalExpected2 = new ItemLocal("produção", 2, "A11");
        List<ItemLocal> localsExpecteds = new ArrayList<>();
        localsExpecteds.add(itemLocalExpected);
        localsExpecteds.add(itemLocalExpected2);
        when(itemLocalRepository.findAll()).thenReturn(localsExpecteds);

        List<ItemLocalResponse> localsExpectedsReponse = new ArrayList<>();
        localsExpectedsReponse.add(mapToResponse(itemLocalExpected));
        localsExpectedsReponse.add(mapToResponse(itemLocalExpected2));

        List<ItemLocalResponse> itemLocalResponses = itemLocalService.findAll();

        verify(itemLocalRepository, times(1)).findAll();
        for(int i=0;i<localsExpecteds.size();i++){
            verifyItemLocalResponse(itemLocalResponses.get(i), localsExpectedsReponse.get(i));
        }
    }

    @Test
    void shouldPatchUpdateItemLocal() {
        ItemLocalPatchRequest itemLocalRequest = new ItemLocalPatchRequest( "produção", null, null);
        Long id = 1L;
        Optional<ItemLocal> itemLocal =  Optional.of(new ItemLocal("teste", 123, "A"));

        when(itemLocalRepository.findById(id)).thenReturn(itemLocal);

        ItemLocal itemLocalShouldBeUpdated = itemLocal.get();
        
        itemLocalShouldBeUpdated.setSectorName(itemLocalRequest.sectorName() == null ? itemLocalShouldBeUpdated.getSectorName() : itemLocalRequest.sectorName());
        itemLocalShouldBeUpdated.setPosition(itemLocalRequest.position() == null ? itemLocalShouldBeUpdated.getPosition() : itemLocalRequest.position());
        itemLocalShouldBeUpdated.setShelf(itemLocalRequest.shelf() == null ? itemLocalShouldBeUpdated.getShelf() : itemLocalRequest.shelf());

        when(itemLocalRepository.save(itemLocalShouldBeUpdated)).thenReturn(itemLocalShouldBeUpdated);

        ItemLocalResponse itemLocalResponse = itemLocalService.patchUpdate(id, itemLocalRequest);
        ItemLocalResponse itemLocalResponseExcepted = new ItemLocalResponse(itemLocalShouldBeUpdated.getLocal_id(),itemLocalShouldBeUpdated.getSectorName(), itemLocalShouldBeUpdated.getPosition(), itemLocalShouldBeUpdated.getShelf());

        verify(itemLocalRepository, times(1)).save(itemLocalShouldBeUpdated);
        verifyItemLocalResponse(itemLocalResponse, itemLocalResponseExcepted);
    }

    @Test
    void shouldPutUpdateItemLocal() {
        ItemLocalRequest itemLocalRequest = new ItemLocalRequest( "produção", 2, "A11");
        Long id = 1L;
        Optional<ItemLocal> itemLocal =  Optional.of(new ItemLocal("teste", itemLocalRequest.position(), itemLocalRequest.shelf()));

        when(itemLocalRepository.findById(id)).thenReturn(itemLocal);

        ItemLocal itemLocalShouldBeUpdated = itemLocal.get();
        itemLocalShouldBeUpdated.setSectorName(itemLocalRequest.sectorName());

        when(itemLocalRepository.save(itemLocalShouldBeUpdated)).thenReturn(itemLocalShouldBeUpdated);

        ItemLocalResponse itemLocalResponse = itemLocalService.putUpdate(id, itemLocalRequest);
        ItemLocalResponse itemLocalResponseExcepted = new ItemLocalResponse(itemLocalShouldBeUpdated.getLocal_id(),itemLocalShouldBeUpdated.getSectorName(), itemLocalShouldBeUpdated.getPosition(), itemLocalShouldBeUpdated.getShelf());

        verify(itemLocalRepository, times(1)).save(itemLocalShouldBeUpdated);
        verifyItemLocalResponse(itemLocalResponse, itemLocalResponseExcepted);
    }

    @Test
    void shouldDeleteItemLocal() {
        ItemLocal itemLocal = createGenericItemLocal();
        itemLocal.setLocal_id(1L);

        when(itemLocalRepository.existsById(itemLocal.getLocal_id())).thenReturn(true);

        itemLocalService.deleteById(itemLocal.getLocal_id());
    }

    @Test
    void shouldNotCreateItemLocalWhenFieldIsNull(){
        ItemLocalRequest itemLocalrequest = new ItemLocalRequest(null, 2, "AB2");
        ItemLocal itemLocalCreated = new ItemLocal(itemLocalrequest.sectorName(), itemLocalrequest.position(), itemLocalrequest.shelf());

        when(itemLocalRepository.save(itemLocalCreated)).thenReturn(itemLocalCreated);

        assertThrows(IllegalArgumentException.class, () -> itemLocalService.create(itemLocalrequest));
    }
    @Test
    void shouldNotCreateItemLocalWhenFieldIsBlank(){
        ItemLocalRequest itemLocalrequest = new ItemLocalRequest("", 2, "AB2");
        ItemLocal itemLocalCreated = new ItemLocal(itemLocalrequest.sectorName(), itemLocalrequest.position(), itemLocalrequest.shelf());

        when(itemLocalRepository.save(itemLocalCreated)).thenReturn(itemLocalCreated);

        assertThrows(IllegalArgumentException.class, () -> itemLocalService.create(itemLocalrequest));
    }

    @Test
    void shouldNotGetItemLocalWhenIdIsNull(){
        ItemLocal itemLocal = createGenericItemLocal();
        Long id = null;

        when(itemLocalRepository.findById(id)).thenReturn(Optional.of(itemLocal));

        assertThrows(IllegalArgumentException.class, () -> itemLocalService.findById(id));
    }

    @Test
    void shouldNotGetItemLocalWhenIdIsSmallerThanOne(){
        ItemLocal itemLocal = createGenericItemLocal();
        Long id = -1L;

        when(itemLocalRepository.findById(id)).thenReturn(Optional.of(itemLocal));

        assertThrows(IllegalArgumentException.class, () -> itemLocalService.findById(id));
    }

    @Test
    void shouldNotUpdateItemLocalWhenIdIsNull() {
        ItemLocalRequest itemLocalRequest = new ItemLocalRequest("produção", 2, "A11");

        assertThrows(IllegalArgumentException.class, () -> itemLocalService.putUpdate(null, itemLocalRequest));
    }

    @Test
    void shouldNotUpdateItemLocalWhenIdIsSmallerThanOne() {
        ItemLocalRequest itemLocalRequest = new ItemLocalRequest("produção", 2, "A11");

        assertThrows(IllegalArgumentException.class, () -> itemLocalService.putUpdate(-1L,itemLocalRequest));
    }

    @Test
    void shouldNotUpdateItemLocalWhenItemLocalIdNotExists() {
        ItemLocalRequest itemLocalRequest = new ItemLocalRequest( "produção", 2, "A11");
        Long id = 10L;
        when(itemLocalRepository.existsById(id)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> itemLocalService.putUpdate(id, itemLocalRequest));
    }

    @Test
    void shouldNotDeleteItemLocalWhenItemLocalIdNotExist() {
        ItemLocal itemLocal = createGenericItemLocal();
        itemLocal.setLocal_id(1L);

        when(itemLocalRepository.existsById(itemLocal.getLocal_id())).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> itemLocalService.deleteById(itemLocal.getLocal_id()));
    }

    @Test
    void shouldNotDeleteItemLocalWhenIdIsNull() {
        ItemLocal itemLocal = createGenericItemLocal();
        itemLocal.setLocal_id(null);

        assertThrows(IllegalArgumentException.class, () -> itemLocalService.deleteById(itemLocal.getLocal_id()));
    }

    private ItemLocalResponse mapToResponse(ItemLocal itemLocal){
        return new ItemLocalResponse(itemLocal.getLocal_id(),itemLocal.getSectorName(), itemLocal.getPosition(), itemLocal.getShelf());
    }

    private ItemLocal createGenericItemLocal(){
        return new ItemLocal("Produção", 2, "AB2");
    }

    private ItemLocalRequest createGenericItemLocalCreationRequest(){
        return new ItemLocalRequest("Produção", 2, "AB2");
    }

    private void verifyItemLocalResponse(ItemLocalResponse itemLocalResponse1, ItemLocalResponse itemLocalResponse2){
        assertEquals(itemLocalResponse1.sectorName(), itemLocalResponse2.sectorName());
        assertEquals(itemLocalResponse1.position(), itemLocalResponse2.position());
        assertEquals(itemLocalResponse1.shelf(), itemLocalResponse2.shelf());
    }
}
