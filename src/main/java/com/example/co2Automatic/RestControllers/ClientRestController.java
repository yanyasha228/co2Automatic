package com.example.co2Automatic.RestControllers;


import com.example.co2Automatic.models.Client;
import com.example.co2Automatic.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restApi/clients")
public class ClientRestController {

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/getClientsByNoNFullPhoneNumber", method = RequestMethod.GET)
    public List<Client> getClientsByNoNFullPhoneNumber(@RequestParam(value = "search_S") String nonFullPhoneNUmber) {

        return clientService.findClientsByNoNFullPhoneNumber(nonFullPhoneNUmber);

    }

    @RequestMapping(value = "/getClientById", method = RequestMethod.GET)
    public Client getClientById(@RequestParam(value = "search_Id") Long id) {

        return clientService.findById(id).orElse(null);
    }
}
