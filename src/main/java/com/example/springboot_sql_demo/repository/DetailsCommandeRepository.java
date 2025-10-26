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

    public List<Map<String, Object>> findTypeRemises() {
        return jdbcTemplate.queryForList("SELECT NumCom, RefProd, Remise," +
                                             "CASE WHEN Remise=0 THEN 'aucune remise'" +
                                             "WHEN Remise BETWEEN 0.01 AND 0.05 THEN 'petite remise'" +
                                             "WHEN Remise BETWEEN 0.06 AND 0.15 THEN 'remise modérée'" +
                                             "ELSE 'remise importante' END AS TypeRemise FROM DetailCommande");
    }

    public List<Map<String, Object>> findRemisesSuperieuresA10() {
        return jdbcTemplate.queryForList("SELECT NumCom, RefProd, ROUND(PrixUnit, 2) AS PrixUnitaireOriginal" +
                                            "Remise * 100 AS RemisePourcent, ROUND(PrixUnit * Remise,2) AS MontantRemise," +
                                            "ROUND(PrixUnit * (1-Remise),2) AS PrixAvec Remise" +
                                            "FROM DetailCommande WHEN Remise > 0.10");
    }
}