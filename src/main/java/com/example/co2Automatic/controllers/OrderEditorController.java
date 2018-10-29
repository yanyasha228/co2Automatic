package com.example.co2Automatic.controllers;

import com.example.co2Automatic.models.Client;
import com.example.co2Automatic.models.Order;
import com.example.co2Automatic.models.PhoneNumber;
import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.services.ClientService;
import com.example.co2Automatic.services.OrderService;
import com.example.co2Automatic.services.PhoneNumberService;
import com.example.co2Automatic.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/editOrder")
public class OrderEditorController {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    ClientService clientService;

    @RequestMapping
    public String editorPage(Model model, @RequestParam(required = false) Long orderId) {

if(orderId!=null) {
    Order order = orderService.findById(orderId).orElse(new Order());
    model.addAttribute("order", order);
} else model.addAttribute("order", new Order());

        model.addAttribute("nowDate", new Date());

        return "editOrder";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String submitOrder(@RequestParam(required = false) Long orderId,
                              @RequestParam(required = false) String inputPhoneNumber,
                              @RequestParam(required = false) String inputDeliveryDate,
                              @RequestParam(required = false) String inputPaymentMethod,
                              @RequestParam(required = false) String inputName,
                              @RequestParam(required = false) String inputSurname,
                              @RequestParam(required = false) String inputCity,
                              @RequestParam(required = false) Integer inputWarehouseNumber,
                              @RequestParam(required = false) String inputOrderComment,
                              @RequestParam(required = false) String[] productNameInput,
                              @RequestParam(required = false) Integer[] productQuaInput,
                              @RequestParam(required = false) Double inputWeight,
                              @RequestParam(required = false) Double inputVolume) {

        orderService.updateOrder(orderId,
                inputPhoneNumber,
                inputDeliveryDate,
                inputPaymentMethod,
                inputName,
                inputSurname,
                inputCity,
                inputWarehouseNumber,
                inputOrderComment,
                productNameInput,
                productQuaInput,
                inputWeight,
                inputVolume);

        return "redirect:../";
    }

    @RequestMapping(value ="/getProductsByNonFullName" , method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getProductsByNonFullName(@RequestParam(value = "search_S") String nonFullNameString)
    {
        return productService.findProductByNonFullProductNameRegardlessOfTheWordsOrder(nonFullNameString);

    }
    @RequestMapping(value ="/getProductByName" , method = RequestMethod.GET)
    @ResponseBody
    public Product getProductByName(@RequestParam(value = "search_S") String productName){

    return productService.findByProductName(productName).orElse(null);
    }

    @RequestMapping(value = "/getClientsByNoNFullPhoneNumber" , method = RequestMethod.GET)
    @ResponseBody
    public List<Client> getClientsByNoNFullPhoneNumber(@RequestParam(value = "search_S") String nonFullPhoneNUmber){

        return clientService.findClientsByNoNFullPhoneNumber(nonFullPhoneNUmber);

    }

    @RequestMapping(value = "/getClientById", method = RequestMethod.GET)
    @ResponseBody
    public Client getClientById(@RequestParam(value = "search_Id") Long id){
        Client client = clientService.findById(id).orElse(null);
        return client;
    }

}
