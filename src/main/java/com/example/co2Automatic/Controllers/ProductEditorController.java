package com.example.co2Automatic.Controllers;

import com.example.co2Automatic.models.MoneyCurrency;
import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.models.ProductStock;
import com.example.co2Automatic.services.ProductCategoryService;
import com.example.co2Automatic.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("products/{id}/edit")
public class ProductEditorController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductCategoryService productCategoryService;

    @GetMapping
    public String editProduct(Model model, @PathVariable Long id) {

        Product productToEdit = productService.findById(id).orElse(new Product());

        model.addAttribute("product", productToEdit);
        model.addAttribute("productCategories", productCategoryService.findAll());
        return "editProduct";
    }

    @PostMapping("submit")
    public String editProductSubmit(Model model,
                                    @ModelAttribute(name = "product") Product product,
                                    @RequestParam Integer productId,
                                    @RequestParam String inputProductName,
                                    @RequestParam String inputProductVendor,
                                    @RequestParam String inputProductCountryOfOrigin,
                                    @RequestParam ProductStock inputProductStock,
                                    @RequestParam String inputProductCategory,
                                    @RequestParam Double inputProductPrice,
                                    @RequestParam Double inputProductWholeSalePrice,
                                    @RequestParam MoneyCurrency inputProductMoneyCurrency,
                                    @RequestParam String inputProductDescription,
                                    @RequestParam String[] inputProductParamName,
                                    @RequestParam String[] inputProductParamValue) {

        int i = 0;
        productService.updateProduct(
                productId,
                inputProductName,
                inputProductVendor,
                inputProductCountryOfOrigin,
                inputProductStock,
                inputProductCategory,
                inputProductPrice,
                inputProductWholeSalePrice,
                inputProductMoneyCurrency,
                inputProductDescription,
                inputProductParamName,
                inputProductParamValue);

        return "redirect:../";
    }

}
