package com.example.springboot_sql_demo.controller;

import com.example.springboot_sql_demo.repository.CommandeRepository;
import com.example.springboot_sql_demo.repository.DetailsCommandeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CommandeController {

    private final DetailsCommandeRepository detailsCommandeRepository;
    private final CommandeRepository commandeRepository;

    public CommandeController(DetailsCommandeRepository detailsCommandeRepository, CommandeRepository commandeRepository) {
        this.detailsCommandeRepository = detailsCommandeRepository;
        this.commandeRepository = commandeRepository;
    }

    @GetMapping("/commandes/{noCommande}/details")
    public List<Map<String, Object>> getDetailsByCommande(@PathVariable int noCommande) {
        return detailsCommandeRepository.findOrderDetailsWithDiscount(noCommande);
    }

    /**
     * @return
     */
    @GetMapping("/commandes/{noCommande}/remise")
    public List<Map<String, Object>> getDetailsByCommandeRemise(
            @PathVariable String noCommande
    ) {
        return detailsCommandeRepository.findTypeRemises();
    }

    @GetMapping("/commandes/dates1")
    public List<Map<String, Object>> getCommandesAvecDates1() {
        return commandeRepository.findCommandesAvecDates1();
    }

    @GetMapping("/commandes/dates2")
    public List<Map<String, Object>> getCommandesAvecDates2() {
        return commandeRepository.findCommandesAvecDates2();
    }

    @GetMapping("/commandes/dates3")
    public List<Map<String, Object>> getCommandesAvecDates3() {
        return commandeRepository.findCommandesAvecDates3();
    }

    @GetMapping("/commandes/dates4")
    public List<Map<String, Object>> getCommandesAvecDates4() {
        return commandeRepository.findCommandesAvecDates4();
    }

    @GetMapping("/commandes/livres")
    public List<Map<String, Object>> getCommandesAvecStatutLivraison() {
        return commandeRepository.findStatutLivraison();
    }

    @GetMapping("/commandes/retards")
    public List<Map<String, Object>> getCommandesRetardes() {
        return commandeRepository.findCommandesEnRetard();
    }

    @GetMapping("/lazy-k")
    public List<Map<String,Object>> commandesLazyK() {
        return commandeRepository.getCommandesLazyK();
    }

    @GetMapping("/commandes-par-messager")
    public List<Map<String,Object>> commandesParMessager() {
        return commandeRepository.countCommandesParMessagerFaitMain();
    }

    // 3. Nombre de commandes des employés sous Patrick Emery
    @GetMapping("/employes/emery/nombre-commandes")
    public Integer countOrdersByEmployeesUnderPatrickEmery() {
        return commandeRepository.countOrdersByEmployeesUnderPatrickEmery();
    }
}
