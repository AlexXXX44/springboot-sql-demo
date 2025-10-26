package com.example.springboot_sql_demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DetailsCommandeRepository {

    private JdbcTemplate jdbcTemplate;

    public void DetailsCommmadeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
// 🔢 Calcul du montant à payer par produit pour la commande 10251
    public List<Map<String, Object>> findOrderDetailsWithDiscount(int noCommande){
        return jdbcTemplate.queryForList("SELECT (PrixUnit * Qte * Remise) AS MontantRemise," +
                "(PrixUnit * Qte * (1-Remise)) AS MontantAPayer" +
                "FROM DetailCommande WHERE NoCommande = ?", noCommande);
    }
}