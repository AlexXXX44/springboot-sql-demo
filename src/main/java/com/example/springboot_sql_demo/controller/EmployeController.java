package com.example.springboot_sql_demo.controller;

import com.example.springboot_sql_demo.repository.EmployeRepository;
import org.springframework.ui.Model;
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

    @GetMapping("/representants")
    public Object getRepresentants(Model model) {
        return model.addAttribute("", employeRepository.countRepresentants());
    }

    @GetMapping("/employes-par-fonction")
    public List<Map<String, Object>> getEmployesParFonction(Model model) {
        return (List<Map<String, Object>>) model.addAttribute("", employeRepository.countEmployesParFonction());
    }

    @GetMapping("/employes-plus-petit-commande")
    public List<Map<String, Object>> getEmployePlusPetitCommande(Model model) {
        return (List<Map<String, Object>>) model.addAttribute("", employeRepository.employeMoinsDeCommandes());
    }

    @GetMapping("/employes-sans-commande")
    public List<Map<String, Object>> getEmployeSansCommande(Model model) {
            return (List<Map<String, Object>>) model.addAttribute("",employeRepository.getEmployesSansCommande());
    }

    @GetMapping("/employes/commandes-france")
    public List<Map<String, Object>> findEmployeesWithFrenchOrders() {
        return employeRepository.findEmployeesWithFrenchOrders();
    }

    @GetMapping("/employes/embauches-infos")
    public List<Map<String, Object>> findInfosEmbauche() {
        return employeRepository.findInfosEmbauche();
    }

    @GetMapping("/employes/commandes")
    public List<Map<String, Object>> commandesParEmploye() {
        return employeRepository.countCommandesParEmploye();
    }

    @GetMapping("/clients/pays-par-employe")
    public List<Map<String, Object>> paysClientsParEmploye() {
        return employeRepository.countPaysClientsParEmploye();
    }

    // 1. Employés n'ayant jamais effectué une commande
    @GetMapping("/employes/sans-commandes")
    public List<Map<String, Object>> getEmployeesWithoutOrders() {
        return employeRepository.getEmployeesWithoutOrders();
    }

    @GetMapping("/employes/tous-pays")
    public List<Map<String, Object>> getEmployeesWithClientsInAllCountries() {
        return employeRepository.getEmployeesWithClientsInAllCountries();
    }
}