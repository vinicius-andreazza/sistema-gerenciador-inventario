package com.sig.sistema_gerenciador_inventario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class PageController {
    @GetMapping("/")
    public String getLoginPage() {
        return "index";
    }
    
    @GetMapping("/userDashboard")
    public String getUserDashboard() {
        return "userDashboard";
    }

    @GetMapping("/supplierDashboard")
    public String getSupplierDashboard() {
        return "supplierDashboard";
    }

    @GetMapping("/productDashboard")
    public String getProducts() {
        return "products";
    }

    @GetMapping("/rawMaterialDashboard")
    public String getRawMaterial() {
        return "rawMaterials";
    }

}
