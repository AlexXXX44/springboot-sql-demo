package com.example.springboot_sql_demo.controller;

import com.example.springboot_sql_demo.repository.ProduitRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ProduitController {

    private final ProduitRepository produitRepository;

    public ProduitController(ProduitRepository produitRepository){
        this.produitRepository = produitRepository;
    }

    @GetMapping("/produits")
    public Object getAllProduits() {
        return produitRepository.findAal();
    }

    @GetMapping("/produits/first10")
    public Object getFirst10() {
        return produitRepository.findAal();
    }

    @GetMapping("/produits/sorted")
    public List<Map<String, Object>> getSortedByPrix() {
        return produitRepository.findAllSortedByPrix();
    }

    @GetMapping("/produits/top3")
    public List<Map<String, Object>> getTop3() {
        return produitRepository.findTop3MostExpensive();
    }

    @GetMapping("/produits/bouteille-canettes")
    public List<Map<String, Object>> getBottlesOrCans() {return produitRepository.findBottlesOrCans();}

    @GetMapping("/produits/tofu-choco")
    public List<Map<String, Object>> getTofuOrChocoUnder100(){
        return produitRepository.findTofuOrChocoUnder100();
    }

    @GetMapping("/produits/fournisseur8")
    public List<Map<String, Object>> getProductsFromSupplier8() {
        return produitRepository.findProductsFromSupplier8Between10And100();
    }
}