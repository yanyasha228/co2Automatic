package com.example.co2Automatic.Controllers;

import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleEntityUpdatingException;
import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.services.ProductCategoryService;
import com.example.co2Automatic.services.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("products/{id}/edit")
public class ProductsEditController {

    private static final Logger logger = Logger.getLogger(ProductsEditController.class);

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
                                    @ModelAttribute(name = "product") Product product) throws ImpossibleEntityUpdatingException {
//        @RequestParam Integer productId,
//        @RequestParam String inputProductName,
//        @RequestParam String inputProductVendor,
//        @RequestParam String inputProductCountryOfOrigin,
//        @RequestParam ProductStock inputProductStock,
//        @RequestParam String inputProductCategory,
//        @RequestParam Double inputProductPrice,
//        @RequestParam Double inputProductWholeSalePrice,
//        @RequestParam MoneyCurrency inputProductMoneyCurrency,
//        @RequestParam String inputProductDescription,
//        @RequestParam String[] inputProductParamName,
//        @RequestParam String[] inputProductParamValue

        productService.update(product);

        return "redirect:../";
    }

    @ExceptionHandler(ImpossibleEntityUpdatingException.class)
    public String handleOrderManipulationException(Model model ,Exception ex) {
        logger.error(ex.toString());

        model.addAttribute("error" , ex.getClass().getName());

        return "error";
    }

}
