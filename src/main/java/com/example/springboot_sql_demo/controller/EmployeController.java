package com.example.springboot_sql_demo.controller;

import com.example.springboot_sql_demo.repository.EmployeRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/employes/commandes-france")
    public Object findEmployeesWithFrenchOrders(Model model) {
        model.addAttribute("rows", employeRepository.findEmployeesWithFrenchOrders());
        return "employes/list_generic";
    }

    @GetMapping("/employes/embauches-infos")
    public Object findInfosEmbauche(Model model) {
        model.addAttribute("rows", employeRepository.findInfosEmbauche());
        return "employes/list_generic";
    }

    @GetMapping("/employes/commandes")
    public Object commandesParEmploye(Model model) {
        model.addAttribute("rows", employeRepository.countCommandesParEmploye());
        return "employes/list_generic";
    }

    @GetMapping("/clients/pays-par-employe")
    public Object paysClientsParEmploye(Model model) {
        model.addAttribute("rows", employeRepository.countPaysClientsParEmploye());
        return "employes/pays-clients-par-employe";
    }

    @GetMapping("/employes-sans-commande")
    public Object getEmployeSansCommande(Model model) {
        model.addAttribute("title", "Employes n'ayant jamais effectué de commandes");
        model.addAttribute("rows", employeRepository.getEmployesSansCommande());
        return "employes/list_generic";
    }

    // 1. Employés n'ayant jamais effectué une commande
    @GetMapping("/employes/sans-commandes")
    public String getEmployeesWithoutOrders(Model model) {
        model.addAttribute("title", "Employés n'ayant jamais effectué aucune commande");
        model.addAttribute("rows", employeRepository.getEmployeesWithoutOrders());
        return "employes/list_generic";
    }

    @GetMapping("/employes/tous-pays")
    public String getEmployeesWithClientsInAllCountries(Model model) {
        model.addAttribute("rows", employeRepository.getEmployeesWithClientsInAllCountries());
        return "employes/list_generic";
    }
}