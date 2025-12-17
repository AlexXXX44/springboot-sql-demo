package com.example.springboot_sql_demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DetailsCommandeRepository {

    private JdbcTemplate jdbcTemplate;

    public DetailsCommandeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 🔢 Calcul du montant à payer par produit pour la commande 10251
    public List<Map<String, Object>> findOrderDetailsWithDiscount(int noCommande){
        return jdbcTemplate.queryForList("SELECT PrixUnit, Qte, Remise," +
                " (PrixUnit * Qte * Remise) AS MontantRemise," +
                "(PrixUnit * Qte * (1-Remise)) AS MontantAPayer " +
                "FROM DetailCommande WHERE NoCom = 10251", noCommande);
    }

    public List<Map<String, Object>> findTypeRemises(Integer noCommande) {
        return jdbcTemplate.queryForList("SELECT NoCom, RefProd, Remise," +
                "CASE WHEN Remise=0 THEN 'aucune remise'" +
                "WHEN Remise BETWEEN 0.01 AND 0.05 THEN 'petite remise'" +
                "WHEN Remise BETWEEN 0.06 AND 0.15 THEN 'remise modérée'" +
                "ELSE 'remise importante' END AS TypeRemise FROM DetailCommande");
    }

    public List<Map<String, Object>> findRemisesSuperieuresA10() {
        return jdbcTemplate.queryForList("SELECT NoCom, RefProd, ROUND(PrixUnit, 2) AS PrixUnitaireOriginal," +
                "Remise AS RemisePourcent, ROUND(PrixUnit * Remise,2) AS MontantRemise," +
                "ROUND(PrixUnit * (1-Remise),2) AS PrixAvecRemise " +
                "FROM DetailCommande WHERE Remise > 0.10");
    }

    public List<Map<String, Object>> quantiteTotaleParClientEtProduit() {
        return jdbcTemplate.queryForList("SELECT c.CodeCli AS CodeClient, cli.Societe AS NomClient," +
                "p.RefProd AS ReferenceProduit, p.Nomprod AS NomProduit, SUM(dc.Qte) AS QuantiteTotale " +
                "FROM DetailCommande dc JOIN Commande c ON dc.NoCom = c.NoCom JOIN Client cli ON c.CodeCli = cli.codeCli " +
                "JOIN Produit p ON dc.RefProd = p.RefProd GROUP BY c.CodeCli, cli.Societe, p.RefProd, p.Nomprod ORDER BY cli.Societe, p.Nomprod");
    }

    public List<Map<String, Object>> montantCommandesAvecEtSansRemise() {
        return jdbcTemplate.queryForList("SELECT dc.NoCom AS NumeroCommande," +
                "ROUND(SUM(dc.PrixUnit * dc.Qte), 2) AS MontantSansRemise," +
                "ROUND(SUM(dc.PrixUnit * dc.Qte * (1-dc.Remise)), 2) AS MontantAvecRemise FROM DetailCommande dc GROUP BY dc.NoCom ORDER BY dc.NoCom");
    }
}