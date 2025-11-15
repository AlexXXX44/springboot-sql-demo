package com.example.springboot_sql_demo.controller;

import com.example.springboot_sql_demo.repository.EmployeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class EmployeController {

    private final EmployeRepository employeRepository;

    public EmployeController(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    @GetMapping("/employes/commandes-france")
    public List<Map<String, Object>> findEmployeesWithFrenchOrders() {
        return employeRepository.findEmployeesWithFrenchOrders();
    }

    @GetMapping("/employes/embauches-infos")
    public List<Map<String, Object>> findInfosEmbauche() {
        return employeRepository.findInfosEmbauche();
    }

    @GetMapping("/commandes")
    public List<Map<String, Object>> commandesParEmploye() {
        return employeRepository.countCommandesParEmploye();
    }

    @GetMapping("/clients/pays")
    public List<Map<String, Object>> paysClientsParEmploye() {
        return employeRepository.countPaysClientsParEmploye();
    }

}