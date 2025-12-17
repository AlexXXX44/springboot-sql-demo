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
        model.addAttribute("title", "Employés et leur responsable");
        model.addAttribute("rows", employeRepository.countRepresentants());
        return "employes/list_generic";
    }

    @GetMapping("/employes-par-fonction")
    public String getEmployesParFonction(Model model) {
        model.addAttribute("title", "Nombre d'employés par fonction");
        model.addAttribute("rows", employeRepository.countEmployesParFonction());
        return "employes/list_generic";
    }

    @GetMapping("/employes-plus-petit-commande")
    public String getEmployePlusPetitCommande(Model model) {
        model.addAttribute("title", "Employés à la commande la plus basse");
        model.addAttribute("rows", employeRepository.employeMoinsDeCommandes());
        return "employes/list_generic";
    }

    @GetMapping("/employes-sans-commande")
    public String getEmployeSansCommande(Model model) {
        model.addAttribute("title", "Employes n'ayant jamais effectué de commandes");
        model.addAttribute("rows", employeRepository.getEmployesSansCommande());
        return "employes/list_generic";
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