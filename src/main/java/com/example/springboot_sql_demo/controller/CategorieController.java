package com.example.springboot_sql_demo.controller;

import com.example.springboot_sql_demo.repository.CategorieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategorieController {

    private final CategorieRepository categorieRepository;

    public CategorieController(CategorieRepository categorieRepository){
        this.categorieRepository = categorieRepository;
    }

    @GetMapping("/categories/descriptions")
    public Object getCategorieDescriptions(Model model) {
        model.addAttribute("", categorieRepository.findAllDescriptions());
        return "";
    }
}