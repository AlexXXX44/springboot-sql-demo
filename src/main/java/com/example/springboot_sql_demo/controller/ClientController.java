package com.example.springboot_sql_demo.controller;

import com.example.springboot_sql_demo.repository.ClientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @GetMapping("/clients/paris")
    public List<Map<String, Object>> getAllFrenchClientsInParis() {
        return clientRepository.findFrenchClientsInParis();
    }

    @GetMapping("/clients/europe")
    public List<Map<String, Object>> getSwissGermanBelgianClients() {
        return clientRepository.findSwissGermanBelgianClients();
    }

    @GetMapping("/clients/sansfax")
    public List<Map<String, Object>> getClientsWithoutFax() {
        return clientRepository.findClientsWithoutFax();
    }

    @GetMapping("/clients/restaurant")
    public List<Map<String, Object>> getClientsWithRestaurantTop3() {
        return clientRepository.findClientsWithRestaurantInName();
    }

    @GetMapping("/clients/pays")
    public List<Map<String, Object>> getDistinctCountries() {
        return clientRepository.findDistinctCountries();
    }

    @GetMapping("/clients/paysvilles")
    public List<Map<String, Object>> getDistinctCountriesAndCities() {
        return clientRepository.findDistinctCountriesAndCities();
    }
}