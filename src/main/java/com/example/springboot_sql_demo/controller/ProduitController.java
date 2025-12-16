package com.example.springboot_sql_demo.controller;

import com.example.springboot_sql_demo.repository.ProduitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ProduitController {

    private final ProduitRepository produitRepository;

    public ProduitController(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @GetMapping("/produits/first10")
    public String getFirst10(Model model) {
        model.addAttribute("produits", produitRepository.findAll());
        return "produits/first10";
    }

    @GetMapping("/produits/sorted")
    public List<Map<String, Object>> getSortedByPrix(Model model) {
        return (List<Map<String, Object>>) model.addAttribute("", produitRepository.findAllSortedByPrix());
    }

    @GetMapping("/produits/top3")
    public List<Map<String, Object>> getTop3(Model model) {
        return (List<Map<String, Object>>) model.addAttribute("", produitRepository.findTop3MostExpensive());
    }

    @GetMapping("/produits/bouteille-canettes")
    public List<Map<String, Object>> getBottlesOrCans(Model model) {
        return (List<Map<String, Object>>) model.addAttribute("", produitRepository.findBottlesOrCans());
    }

    @GetMapping("/produits/tofu-choco")
    public List<Map<String, Object>> getTofuOrChocoUnder100(Model model) {
        return (List<Map<String, Object>>) model.addAttribute("", produitRepository.findTofuOrChocoUnder100());
    }

    @GetMapping("/produits/fournisseur8")
    public List<Map<String, Object>> getProductsFromSupplier8(Model model) {
        return (List<Map<String, Object>>) model.addAttribute("", produitRepository.findProductsFromSupplier8Between10And100());
    }

    @GetMapping("/produits/dispo")
    public List<Map<String, Object>> getProductsDispo(Model model) {
        model.addAttribute("", produitRepository.findDisponibiliteProduits());
        return List.of();
    }

    @GetMapping("/produits-moins-50")
    public Number getNbProduitsMoins50(Model model) {
        return (Number) model.addAttribute("", produitRepository.countProduitsMoins50());
    }

    @GetMapping("/produits-cat2-stock10")
    public Object getNbProduitsCat2Stock10(Model model) {
        return model.addAttribute("", produitRepository.countProduitsCat2Stock10());
    }

    @GetMapping("/produits-cat1-four1-18")
    public Object getNbProduitsCAt1Four1ou18(Model model) {
        return model.addAttribute("", produitRepository.countProduitsCat1Four1ou18());
    }

    @GetMapping("/categorie-par-fournisseur")
    public List<Map<String, Object>> getCategoriesParFournisseur(Model model) {
        for (Map<String, Object> stringObjectMap : (List<Map<String, Object>>) model.addAttribute(
                "", produitRepository.countCategoriesParFournisseur())) {          }
        produitRepository.fournisseursUnSeulProduit();
        produitRepository.categoriesPrixMoyenSup150();
        produitRepository.fournisseursUneCategorie();
        produitRepository.getProduitsAvecFournisseurs();
        return List.of();
    }
}