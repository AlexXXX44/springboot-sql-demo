package com.example.springboot_sql_demo.controller;

import com.example.springboot_sql_demo.repository.StatistiquesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    private StatistiquesRepository statistiquesRepository;

    public StatsController(StatistiquesRepository statistiquesRepository) {
        this.statistiquesRepository = statistiquesRepository;
    }

    @GetMapping("/representants")
    public Object getRepresentants() {
        return statistiquesRepository.countRepresentants();
    }

    @GetMapping("/produits-moins-50")
    public Number getNbProduitsMoins50() {
        return statistiquesRepository.countProduitsMoins50();
    }

    @GetMapping("/produits-cat2-stock10")
    public int getNbProduitsCat2Stock10() {
        return statistiquesRepository.countProduitsCat2Stock10();
    }

    @GetMapping("/produits-cat1-four1-18")
    public int getNbProduitsCAt1Four1ou18() {
        return statistiquesRepository.countProduitsCat1Four1ou18();
    }

    @GetMapping("/pays-livraison")
    public int getNbPaysLivraison() {
        return statistiquesRepository.countPaysLivraison();
    }

    @GetMapping("/commande280316")
    public int getCommande280316() {
        return statistiquesRepository.countCommandes280316();
    }

    @GetMapping("/port-moyen-quick")
    public double getCoutMoyenPortQuick() {
        return statistiquesRepository.getCoutMoyenPortQuick();
    }

    @GetMapping("/port-min-max")
    public double getCoutPortMinMax() {
        return statistiquesRepository.getCoutPortMinMax();
    }

    @GetMapping("/frais-port-par-messager")
    public List<Map<String, Object>> getTotalFraisPortParMessager() {
        return statistiquesRepository.getTotalFraisPortParMessager();
    }

    @GetMapping("/employes-par-fonction")
    public List<Map<String, Object>> getEmployesParFonction() {
        return statistiquesRepository.countEmployesParFonction();
    }

    @GetMapping("/moyen-port-par-messager")
    public List<Map<String, Object>> getMoyenPortParMessager() {
        return statistiquesRepository.avgPortParMessager();
    }

    @GetMapping("/categorie-par-fournisseur")
    public List<Map<String, Object>> getCategoriesParFournisseur() {
        return statistiquesRepository.countCategoriesParFournisseur();
    }

    @GetMapping("/prix-par-fournisseur-et-categorie")
    public List<Map<String, Object>> getPrixMoyenParFournisseurEtCategorie() {
        return statistiquesRepository.avgPrixParFournisseurEtCategorie();
    }


}