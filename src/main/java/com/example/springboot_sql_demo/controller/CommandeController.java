package com.example.springboot_sql_demo.controller;

import com.example.springboot_sql_demo.repository.CommandeRepository;
import com.example.springboot_sql_demo.repository.DetailsCommandeRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandeController {

    private final DetailsCommandeRepository detailsCommandeRepository;
    private final CommandeRepository commandeRepository;

    public CommandeController(DetailsCommandeRepository detailsCommandeRepository, CommandeRepository commandeRepository) {
        this.detailsCommandeRepository = detailsCommandeRepository;
        this.commandeRepository = commandeRepository;
    }

    @GetMapping("/pays-livraison")
    public Object getNbPaysLivraison(Model model) {
        model.addAttribute("rows", commandeRepository.countPaysLivraison());
        model.addAttribute("title", "NbPaysLivraison");
        return "commandes/list_generic";
    }

    @GetMapping("/commande280316")
    public Object getCommande280316(Model model) {
        model.addAttribute("rows", commandeRepository.countCommandes280316());
        model.addAttribute("title", "Commande280316");
        return "commandes/list_generic";
    }

    @GetMapping("/commandes/{noCommande}/details")
    public Object getDetailsByCommande(@PathVariable int noCommande, Model model) {
        model.addAttribute("title", "Details PAr commande");
        model.addAttribute("rows", detailsCommandeRepository.findOrderDetailsWithDiscount(noCommande));
        return "commandes/list_generic";
    }

    @GetMapping("/commandes/totaux")
    public Object getQteTotalsByClient(Model model) {
        model.addAttribute("title", "QuantiteTotalParClient");
        model.addAttribute("rows", detailsCommandeRepository.quantiteTotaleParClientEtProduit());
        return "commandes/list_generic";
    }

    @GetMapping("/commandes/top5clients")
    public Object getTop5Client(Model model) {
        model.addAttribute("title", "5 meilleurs clients");
        model.addAttribute("rows", commandeRepository.top5ClientsParCommandes());
        return "commandes/list_generic";
    }

    @GetMapping("/commandes/montantsTotalCom")
    public Object getMontantsTotal(Model model) {
        model.addAttribute("title", "Montant Total");
        model.addAttribute("rows", detailsCommandeRepository.montantCommandesAvecEtSansRemise());
        return "commandes/list_generic";
    }

    @GetMapping("/commandes/ComLazyK")
    public Object getClientsLazyK(Model model) {
        model.addAttribute("title", "Client Commandes LAzy");
        model.addAttribute("rows", commandeRepository.getCommandesClientLazyK());
        return "commandes/commandes-lazy-k";
    }

    @GetMapping("/commandes/{noCommande}/remise")
    public String getDetailsByCommandeRemise(
            @PathVariable Integer noCommande, Model model
    ) {
        model.addAttribute("title", "Remise par commandes");
        model.addAttribute("rows",
                detailsCommandeRepository.findTypeRemises(noCommande));
        return "commandes/list_generic";
    }

    @GetMapping("/commandes/dates1")
    public String getCommandesAvecDates1(Model model) {
        model.addAttribute("rows", commandeRepository.findCommandesAvecDates1());
        return "commandes/list_generic";
    }

    @GetMapping("/commandes/dates2")
    public String getCommandesAvecDates2(Model model) {
        model.addAttribute("rows", commandeRepository.findCommandesAvecDates2());
        return "commandes/list_generic";
    }

    @GetMapping("/commandes/dates3")
    public String getCommandesAvecDates3(Model model) {
        model.addAttribute("rows", commandeRepository.findCommandesAvecDates3());
        return "commandes/list_generic";
    }

    @GetMapping("/commandes/dates4")
    public String getCommandesAvecDates4(Model model) {
        model.addAttribute("rows", commandeRepository.findCommandesAvecDates4());
        return "commandes/list_generic";
    }

    @GetMapping("/commandes/livres")
    public String getCommandesAvecStatutLivraison(Model model) {
        model.addAttribute("title", "Commandes livrées");
        model.addAttribute("rows", commandeRepository.findStatutLivraison());
        return "commandes/list_generic";
    }

    @GetMapping("/commandes/retards")
    public String getCommandesRetardes(Model model) {
        model.addAttribute("title", "Commandes livrées en retard");
        model.addAttribute("rows", commandeRepository.findCommandesEnRetard());
        return "commandes/list_generic";
    }

    @GetMapping("/lazy-k")
    public String commandesLazyK(Model model) {
        model.addAttribute("rows", commandeRepository.getCommandesLazyK());
        return "commandes/commandes-lazy-k";
    }

    @GetMapping("/commandes-par-messager")
    public String commandesParMessager(Model model) {
        model.addAttribute("rows", commandeRepository.countCommandesParMessagerFaitMain());
        return "commandes/commandes-par-messager";
    }

    // 3. Nombre de commandes des employés sous Patrick Emery
    @GetMapping("/employes/emery/nombre-commandes")
    public String countOrdersByEmployeesUnderPatrickEmery(Model model) {
        model.addAttribute("rows", commandeRepository.countOrdersByEmployeesUnderPatrickEmery());
        return "commandes/commandes-par-employe";
    }
}
