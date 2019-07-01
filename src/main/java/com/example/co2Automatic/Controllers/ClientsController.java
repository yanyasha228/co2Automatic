package com.example.co2Automatic.Controllers;


import com.example.co2Automatic.models.Client;
import com.example.co2Automatic.models.ModelEnums.ProductStock;
import com.example.co2Automatic.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    ClientService clientService;

    @RequestMapping
    public String clientList(Model model,
                               @PageableDefault(sort = {"creationDate"} , direction = Sort.Direction.DESC ,size = 5) Pageable pageable,
                               @RequestParam Optional<ProductStock> productStockSorting,
                               @RequestParam Optional<Integer> productCategorySortingId,
                               @RequestParam Optional<String> productNameSearchInput) {


        /**
         * Delegate showing product list logic into the {@link ProductListPageHelper}
         */
//        productListPageHelper.validatePageShowingState(productsPageNumber, productsPageSize, productsStockSorting, productsCategorySortingId , productNameSearchInput);
//        Page<Client> clientsPage = clientService.getClientssWithPagination(pageable,
//                productStockSorting.orElse(null),
//                productCategoryService.findById(productCategorySortingId.orElse(0)).orElse(null),
//                productNameSearchInput.orElse(""));

        Page<Client> clientsPage = clientService.findAllWithPagination(pageable);



//        model.addAttribute("productStockSorting" , productStockSorting.orElse(null));
//
//        model.addAttribute("productCategorySortingId" , productCategorySortingId.orElse(0));
//
//        model.addAttribute("productNameSearchInput" , productNameSearchInput.orElse(""));


        model.addAttribute("clientsPage",
                clientsPage);
//            model.addAttribute("productsList",
//                    productService.findProductsByProductStockWithPagination(productListPageHelper.getProductsStockSorting(), PageRequest.of(productListPageHelper.getCurrentPageNumber(),
//                            productListPageHelper.getPageSize(),
//                            Sort.Direction.ASC,
//                            "id")));

//        model.addAttribute("productCategories" , productCategoryService.findAll());

        return "clients";
    }
}
