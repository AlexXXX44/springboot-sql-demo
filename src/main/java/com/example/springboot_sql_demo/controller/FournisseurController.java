package com.example.springboot_sql_demo.controller;

import com.example.springboot_sql_demo.repository.FournisseurRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FournisseurController {

    private final FournisseurRepository fournisseurRepository;

    public FournisseurController(FournisseurRepository fournisseurRepository){
        this.fournisseurRepository = fournisseurRepository;
    }

    @GetMapping("/fournisseurs/france")
    public Object getFrenchSuppliers(){
        return fournisseurRepository;
    }
}