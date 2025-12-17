package com.example.springboot_sql_demo.controller;

import com.example.springboot_sql_demo.repository.CategorieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class CategorieController {

    private final CategorieRepository categorieRepository;

    public CategorieController(CategorieRepository categorieRepository){
        this.categorieRepository = categorieRepository;
    }

    @GetMapping("/categories/descriptions")
    public String getCategorieDescriptions(Model model) {
        List<Map<String, Object>> rows = categorieRepository.findAllDescriptions();
        model.addAttribute("rows", rows);
        model.addAttribute("title", "Descriptions des catégories");
        return "categories/descriptions";
    }
}