package com.example.springboot_sql_demo.controller;

import com.example.springboot_sql_demo.repository.DetailsCommandeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DetailsCommandeController {

    private final DetailsCommandeRepository detailsCommandeRepository;

    public DetailsCommandeController(DetailsCommandeRepository detailsCommandeRepository) {
        this.detailsCommandeRepository = detailsCommandeRepository;
    }

    @GetMapping("/commandes/{noCommande}/details")
    public List<Map<String, Object>> getDetailsByCommande(@PathVariable int noCommande) {
        return detailsCommandeRepository.findOrderDetailsWithDiscount(noCommande);
    }

}
