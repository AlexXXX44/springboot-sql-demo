package com.example.springboot_sql_demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class StatistiquesRepository {

    private final JdbcTemplate jdbcTemplate;

    public StatistiquesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public double getCoutMoyenPortQuick() {
        return jdbcTemplate.queryForObject("SELECT ROUND(AVG(Port)) AS CoutMoyenPort FROM Commande WHERE CodeCli ='QUICK'", Double.class);
    }

    public double getCoutPortMinMax() {
        return jdbcTemplate.queryForObject("SELECT MIN(Port) AS CoutPortMinimum, MAX(Port) AS CoutPortMaximum FROM Commande", Double.class);
    }

    public List<Map<String, Object>> getTotalFraisPortParMessager() {
        return jdbcTemplate.queryForList("SELECT NoMess AS NumeroMessager, ROUND(SUM(Port),2) AS TotalFraisPort FROM Commande GROUP BY NoMess ORDER BY NoMess");
    }

    public List<Map<String, Object>> avgPortParMessager() {
        return jdbcTemplate.queryForList("SELECT NoMess AS NumeroMessager, ROUND(AVG(Port), 2) AS MontantMoyenPort FROM Commande GROUP BY NoMess ORDER BY NoMess");
    }

    public List<Map<String, Object>> avgPrixParFournisseurEtCategorie() {
        return jdbcTemplate.queryForList("SELECT NoFour AS NumeroFournisseur, CodeCateg AS CodeCategorie, ROUND(AVG(PrixUnit),2) AS PrixMoyen " +
                "FROM Produit GROUP BY NoFour, CodeCateg ORDER BY NoFour, CodeCateg");
    }
//
//    public List<Map<String, Object>> produitsEtFournisseurs() {
//        return jdbcTemplate.queryForList("SELECT p.RefProd AS ReferenceProduit, p.NomProd AS NomProduit, p.PrixUnit AS PrixUnitaire," +
//                "f.NumFourn AS NumeroFournisseur, f.NomFourn AS NomFournisseur, f.Ville AS VilleFournisseur" +
//                "f.Pays AS PaysFournisseur FROM Produit p JOIN Fournisseur f ON p.NumFourn = f.NumFourn" +
//                "ORDER BY f.NomFourn, p.NomProd");
//    }

}