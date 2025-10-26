package com.example.springboot_sql_demo.controller;

import com.example.springboot_sql_demo.repository.ProduitRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProduitController {

    private final ProduitRepository produitRepository;

    public ProduitController(ProduitRepository produitRepository){
        this.produitRepository = produitRepository;
    }

    @GetMapping("/produits")
    public Object getAllProduits() {
        return produitRepository;
    }

    @GetMapping("/produits/first10")
    public Object getFirst10() {
        return produitRepository;
    }

    @GetMapping("/produits/sorted")
    public Object getSortedByPrix() {
        return produitRepository;
    }

    @GetMapping("/produits/top3")
    public Object getTop3() {
        return produitRepository;
    }

    @GetMapping("/produits/bouteille-canettes")
    public Object getBottlesOrCans() {return produitRepository;}

    @GetMapping("/produits/tofu-choco")
    public Object getTofuOrChocoUnder100(){
        return produitRepository;
    }

    @GetMapping("/produits/fournisseur8")
    public ProduitRepository getProductsFromSupplier8() {
        return produitRepository;
    }
}