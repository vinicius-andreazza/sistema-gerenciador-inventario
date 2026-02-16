package com.sig.sistema_gerenciador_inventario.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.sig.sistema_gerenciador_inventario.mapper.models.ProductMapper;
import com.sig.sistema_gerenciador_inventario.mapper.response.ProductResponseMapper;
import com.sig.sistema_gerenciador_inventario.model.ItemLocal;
import com.sig.sistema_gerenciador_inventario.model.Product;
import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ProductPatchRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.request.ProductRequest;
import com.sig.sistema_gerenciador_inventario.model.dto.response.ProductResponse;
import com.sig.sistema_gerenciador_inventario.model.enums.CategoryItem;
import com.sig.sistema_gerenciador_inventario.model.enums.StatusItem;
import com.sig.sistema_gerenciador_inventario.model.enums.TypeItem;
import com.sig.sistema_gerenciador_inventario.repository.ItemRepository;
import com.sig.sistema_gerenciador_inventario.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockitoBean
    private ItemRepository itemRepository;

    @MockitoBean
    private ProductRepository productRepository;

    @MockitoBean
    private ItemService itemService;

    @Test
    void shouldCreateProduct() {
        ProductRequest productRequest = generateGenericRequest();

        Product product = ProductMapper.productMap(productRequest);
        ProductResponse expectedResponse = ProductResponseMapper.productMap(product);

        when(itemRepository.save(product)).thenReturn(product);

        ProductResponse response = productService.create(productRequest);

        verify(itemRepository, times(1)).save(product);
        verifyProductResponse(response, expectedResponse);
    }
    /* 
    @Test
    void shouldGetAllProducts() {
        Product product1 = ProductMapper.productMap(generateGenericRequest());
        product1.setItem_id(1L);

        Product product2 = ProductMapper.productMap(generateGenericRequest());;
        product2.setItem_id(2L);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        when(productRepository.findAll()).thenReturn(products);

        List<ProductResponse> responses = productService.findAll(PageRequest.of(0, 10));

        assertEquals(2, responses.size());
    }*/

    @Test
    void shouldGetProductById() {
        Long id = 1L;
        Product product = ProductMapper.productMap(generateGenericRequest());;
        product.setItem_id(id);

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        ProductResponse response = productService.findById(id);

        assertEquals(id, response.getId());
    }

    @Test
    void shouldPutUpdateProduct() {
        Long id = 1L;

        ProductRequest productRequest = generateGenericRequest();

        Product existingProduct = ProductMapper.productMap(productRequest);
        existingProduct.setMeasure("sad");
        existingProduct.setItem_id(id);

        Product updatedProduct = ProductMapper.productMap(productRequest);
        updatedProduct.setItem_id(id);

        ProductResponse expectedResponse = ProductResponseMapper.productMap(updatedProduct);

        when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));
        when(itemRepository.save(updatedProduct)).thenReturn(updatedProduct);

        ProductResponse response = productService.putUpdate(id, productRequest);

        verify(itemRepository, times(1)).save(updatedProduct);
        verifyProductResponse(expectedResponse, response);
    }

    @Test
    void shouldPatchUpdateProduct() {
        Long id = 1L;

        ProductPatchRequest patchRequest = new ProductPatchRequest(null, null, null, null,null,null,null,null,null,null,150.0,null,null,null,null);

        Product product = ProductMapper.productMap(generateGenericRequest());
        product.setItem_id(id);
        product.setValue(100.0);

        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(itemRepository.save(product)).thenReturn(product);

        ProductResponse response = productService.patchUpdate(id, patchRequest);

        verify(itemService, times(1)).patchItemFields(product, patchRequest);
        verify(itemRepository, times(1)).save(product);
    }

    @Test
    void shouldDeleteProductById() {
        Long id = 1L;

        productService.deleteById(id);

        verify(itemRepository, times(1)).deleteById(id);
    }

    @Test
    void shouldNotGetProductByIdWhenNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> productService.findById(1L));
    }

    private ProductRequest generateGenericRequest() {
        return new ProductRequest(
                "Produto A",
                CategoryItem.Embalagem,
                TypeItem.PRODUTO,
                "Descrição",
                10,
                2,
                "UN",
                StatusItem.ATIVO,
                1L,
                1L,
                100.0,
                10.0,
                5.0,
                3.0,
                2.0);
    }

    private void verifyProductResponse(ProductResponse r1, ProductResponse r2) {
        assertEquals(r1.getId(), r2.getId());
        assertEquals(r1.getName(), r2.getName());
        assertEquals(r1.getCategory(), r2.getCategory());
        assertEquals(r1.getStatus(), r2.getStatus());
        assertEquals(r1.getDescription(), r2.getDescription());
        assertEquals(r1.getQuantity(), r2.getQuantity());
        assertEquals(r1.getMinimiumQuantity(), r2.getMinimiumQuantity());
        assertEquals(r1.getMeasure(), r2.getMeasure());
        assertEquals(r1.getValue(), r2.getValue());
        assertEquals(r1.getHeight(), r2.getHeight());
        assertEquals(r1.getWeight(), r2.getWeight());
        assertEquals(r1.getLength(), r2.getLength());
        assertEquals(r1.getDepth(), r2.getDepth());
    }
}
