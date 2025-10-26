package com.example.springboot_sql_demo.controller;

import com.example.springboot_sql_demo.repository.ClientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @GetMapping("/clients/paris")
    public ClientRepository getAllFrenchClientsInParis() {
        return clientRepository;
    }

    @GetMapping("/clients/europe")
    public Object getSwissGermanBelgianClients() {
        return clientRepository;
    }

    @GetMapping("/clients/sansfax")
    public Object getClientsWithoutFax() {
        return clientRepository;
    }

    @GetMapping("/clients/restaurant")
    public Object getClientsWithRestaurantTop3() {
        return clientRepository;
    }

    @GetMapping("/clients/pays")
    public Object getDistinctCountries() {
        return clientRepository;
    }

    @GetMapping("/clients/paysvilles")
    public Object getDistinctCountriesAndCities() {
        return clientRepository;
    }
}