package com.example.springboot_sql_demo.controller;

import com.example.springboot_sql_demo.repository.ClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Controller
//@GetMapping("/clients")
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/paris")
    public String getAllFrenchClientsInParis(Model model) {
        List<Map<String, Object>> rows = clientRepository.findFrenchClientsInParis();
        model.addAttribute("rows", rows);
        model.addAttribute("title", "Clients français à Paris");
        return "clients/list_generic";
    }

    @GetMapping("/europe")
    public String getSwissGermanBelgianClients(Model model) {
        List<Map<String, Object>> rows = clientRepository.findSwissGermanBelgianClients();
        model.addAttribute("rows", rows);
        model.addAttribute("title", "Clients (Suisse, Allemagne, Belgique)");
        return "clients/list_generic";
    }

    @GetMapping("/sansfax")
    public String getClientsWithoutFax(Model model) {
        List<Map<String, Object>> rows = clientRepository.findClientsWithoutFax();
        model.addAttribute("rows", rows);
        model.addAttribute("title", "Clients sans fax");
        return "clients/list_generic";
    }

    @GetMapping("/restaurant")
    public String getClientsWithRestaurantTop3(Model model) {
        List<Map<String, Object>> rows = clientRepository.findClientsWithRestaurantInName();
        model.addAttribute("rows", rows);
        model.addAttribute("title", "Clients contenant 'restaurant' dans la société");
        return "clients/list_generic";
    }

    // Pays distincts des clients
    @GetMapping("/pays")
    public String getDistinctCountries(Model model) {
        List<Map<String, Object>> rows = clientRepository.findDistinctCountries();
        model.addAttribute("rows", rows);
        model.addAttribute("title", "Pays distincts des clients");
        return "clients/list_generic";
    }

    // Pays et villes distincts triés
    @GetMapping("/paysvilles")
    public String getDistinctCountriesAndCities(Model model) {
        List<Map<String, Object>> rows = clientRepository.findDistinctCountriesAndCities();
        model.addAttribute("rows", rows);
        model.addAttribute("title", "Pays et villes distincts (triés)");
        return "clients/list_generic";
    }

    // Societe contient contact
    @GetMapping("/nom-contains-contact")
    public String getClientsNomContientContact(Model model) {
        List<Map<String, Object>> rows = clientRepository.findClientsNomInclutContact();
        model.addAttribute("rows", rows);
        model.addAttribute("title", "Clients dont la société contient le nom du contact");
        return "clients/list_generic";
    }

    // Clients formatés (concat, lower, replace, etc.)
    @GetMapping("")
    public String getClientsWithFormattedData(Model model) {
        List<Map<String, Object>> rows = clientRepository.findClientsWithFormattedData();
        model.addAttribute("rows", rows);
        model.addAttribute("title", "Clients formatés (adresse, code, etc.)");
        return "clients/list_generic";
    }

// Produits par client et catégorie (peut renvoyer des nulls)
//    @GetMapping("/produits-par-client-categorie")
//    public String produitsClientCategorie(Model model) {
//        List<Map<String, Object>> rows = clientRepository.countProduitsClientCategorie();
//        return "";
//    }

    // Clients ayant commandé Camembert Pierrot
    @GetMapping("/camembert-pierrot")
    public String getClientsWhoOrderedCamembertPierrot(Model model) {
        List<Map<String, Object>> rows = clientRepository.getClientsWhoOrderedCamembertPierrot();
        model.addAttribute("rows", rows);
        model.addAttribute("title", "Clients ayant commandé 'Camembert Pierrot'");
        return "clients/list_generic";
    }

    // Clients ayant commandé tous les produits d'Exotic Liquids
    @GetMapping("/tous-produits-exotic")
    public String getClientsWhoOrderedAllExoticProducts(Model model) {
        List<Map<String, Object>> rows = clientRepository.getClientsWhoOrderedAllExoticProducts();
        model.addAttribute("rows", rows);
        model.addAttribute("title", "Clients ayant commandé tous les produits 'Exotic Liquids'");
        return "clients/list_generic";
    }
}