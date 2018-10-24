package com.example.co2Automatic.controllers;

import com.example.co2Automatic.models.Client;
import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/clients/editClient")
public class ClientEditorController {

    @Autowired
    ClientService clientService;

    @GetMapping
    public String editProduct(Model model, @RequestParam(required = false , defaultValue = "0") Long id) {

        Client clientToEdit = clientService.findById(id).orElse(new Client());

        model.addAttribute("client", clientToEdit);

        return "editProduct";
    }
}
