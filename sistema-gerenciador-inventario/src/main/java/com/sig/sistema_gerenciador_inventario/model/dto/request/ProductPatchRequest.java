package com.sig.sistema_gerenciador_inventario.model.dto.request;

import com.sig.sistema_gerenciador_inventario.model.ItemLocal;
import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.model.enums.CategoryItem;
import com.sig.sistema_gerenciador_inventario.model.enums.StatusItem;
import com.sig.sistema_gerenciador_inventario.model.enums.TypeItem;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import lombok.Getter;

@Getter
public class ProductPatchRequest extends ItemPatchRequest {
    @Null
    private Double value;

    @Null
    private Double weight;

    @Null
    private Double height;

    @Null
    private Double length;

    @Null
    private Double depth;

    

    public ProductPatchRequest(@Null @NotEmpty String name, @Null CategoryItem category, @Null TypeItem typeItem,
            @Null @NotEmpty String description, @Null Integer quantity, @Null Integer minimiumQuantity,
            @Null @NotEmpty String measure, @Null StatusItem status, @Null User user, @Null ItemLocal itemLocal,
            @Null Double value, @Null Double weight, @Null Double height, @Null Double length, @Null Double depth) {
        super(name, category, typeItem, description, quantity, minimiumQuantity, measure, status, user, itemLocal);
        this.value = value;
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.depth = depth;
    }



    public ProductPatchRequest(@Null @NotEmpty String name, @Null CategoryItem category, @Null TypeItem typeItem,
            @Null @NotEmpty String description, @Null Integer quantity, @Null Integer minimiumQuantity,
            @Null @NotEmpty String measure, @Null StatusItem status, @Null User user, @Null ItemLocal itemLocal) {
        super(name, category, typeItem, description, quantity, minimiumQuantity, measure, status, user, itemLocal);
    }



    public ProductPatchRequest() {
    }
    
    
}
