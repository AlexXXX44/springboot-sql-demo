package com.example.springboot_sql_demo.controller;

import com.example.springboot_sql_demo.repository.FournisseurRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.SequencedCollection;

@RestController
public class FournisseurController {

    private final FournisseurRepository fournisseurRepository;

    public FournisseurController(FournisseurRepository fournisseurRepository){
        this.fournisseurRepository = fournisseurRepository;
    }

    @GetMapping("/fournisseurs/france")
    public List<Map<String, Object>> getFrenchSuppliers(){
        return fournisseurRepository.findFrenchSuppliers();
    }

    @GetMapping("/produits")
    public List<Map<String,Object>> produitsParFournisseur() {
        return fournisseurRepository.countProduitsParFournisseur();
    }

    @GetMapping("/produits-par-pays")
    public List<Map<String, Object>> produitsParPays() {
        return fournisseurRepository.countProduitsParPays();
    }

}