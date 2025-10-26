package com.example.springboot_sql_demo.controller;

import com.example.springboot_sql_demo.repository.CategorieRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategorieController {

    private final CategorieRepository categorieRepository;

    public CategorieController(CategorieRepository categorieRepository){
        this.categorieRepository = categorieRepository;
    }

    @GetMapping("/categories/descriptions")
    public Object getCategorieDescriptions() {
        return categorieRepository;
    }
}