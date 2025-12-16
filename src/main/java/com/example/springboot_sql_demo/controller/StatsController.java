package com.example.springboot_sql_demo.controller;

import com.example.springboot_sql_demo.repository.StatistiquesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    private StatistiquesRepository statistiquesRepository;

    public StatsController(StatistiquesRepository statistiquesRepository) {
        this.statistiquesRepository = statistiquesRepository;
    }

    @GetMapping("/port-moyen-quick")
    public Object getCoutMoyenPortQuick(Model model) {
        return model.addAttribute("",statistiquesRepository.getCoutMoyenPortQuick());
    }

    @GetMapping("/port-min-max")
    public Object getCoutPortMinMax(Model model) {
        return model.addAttribute("",statistiquesRepository.getCoutPortMinMax());
    }

    @GetMapping("/frais-port-par-messager")
    public List<Map<String, Object>> getTotalFraisPortParMessager(Model model) {
        return (List<Map<String, Object>>) model.addAttribute("",statistiquesRepository.getTotalFraisPortParMessager());
    }

    @GetMapping("/moyen-port-par-messager")
    public List<Map<String, Object>> getMoyenPortParMessager(Model model) {
        return (List<Map<String, Object>>) model.addAttribute("",statistiquesRepository.avgPortParMessager());
    }

    @GetMapping("/prix-par-fournisseur-et-categorie")
    public List<Map<String, Object>> getPrixMoyenParFournisseurEtCategorie(Model model) {
        return (List<Map<String, Object>>) model.addAttribute("",statistiquesRepository.avgPrixParFournisseurEtCategorie());
    }
}