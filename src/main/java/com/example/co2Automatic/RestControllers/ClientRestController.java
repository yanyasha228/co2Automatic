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

    @GetMapping(params = "notFullClientPhoneNumber")
    public List<Client> getClientsByNoNFullPhoneNumber(@RequestParam(value = "notFullClientPhoneNumber") String nonFullPhoneNUmber) {
        return clientService.findClientsByNoNFullPhoneNumber(nonFullPhoneNUmber);
    }

    @GetMapping(params = "clientPhoneNumber")
    public List<Client> getClientByPhoneNumber(@RequestParam(value = "clientPhoneNumber") String nonFullPhoneNUmber) {
        return clientService.findClientsByNoNFullPhoneNumber(nonFullPhoneNUmber);
    }

    @GetMapping(value = "/{id}")
    public Client getClientById(@PathVariable Long id) {

        return clientService.findById(id).orElse(null);
    }
}
