package com.sig.sistema_gerenciador_inventario.model.dto.request;

import java.util.Set;

import com.sig.sistema_gerenciador_inventario.model.ItemLocal;
import com.sig.sistema_gerenciador_inventario.model.Supplier;
import com.sig.sistema_gerenciador_inventario.model.User;
import com.sig.sistema_gerenciador_inventario.model.enums.CategoryItem;
import com.sig.sistema_gerenciador_inventario.model.enums.StatusItem;
import com.sig.sistema_gerenciador_inventario.model.enums.TypeItem;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import lombok.Getter;

@Getter
public class RawMaterialPatchRequest extends ItemPatchRequest {
    @Null
    private Integer batch;

    @Null
    private Double unitValue;

    @Null
    private Set<Supplier> supplier;

    public RawMaterialPatchRequest(@Null @NotEmpty String name, @Null CategoryItem category, @Null TypeItem typeItem,
            @Null @NotEmpty String description, @Null Integer quantity, @Null Integer minimiumQuantity,
            @Null @NotEmpty String measure, @Null StatusItem status, @Null User user, @Null ItemLocal itemLocal,
            @Null Integer batch, @Null Double unitValue, @Null Set<Supplier> supplier) {
        super(name, category, typeItem, description, quantity, minimiumQuantity, measure, status, user, itemLocal);
        this.batch = batch;
        this.unitValue = unitValue;
        this.supplier = supplier;
    }

    public RawMaterialPatchRequest(@Null @NotEmpty String name, @Null CategoryItem category, @Null TypeItem typeItem,
            @Null @NotEmpty String description, @Null Integer quantity, @Null Integer minimiumQuantity,
            @Null @NotEmpty String measure, @Null StatusItem status, @Null User user, @Null ItemLocal itemLocal) {
        super(name, category, typeItem, description, quantity, minimiumQuantity, measure, status, user, itemLocal);
    }

    
}
