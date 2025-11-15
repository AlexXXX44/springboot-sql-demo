package com.example.springboot_sql_demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class FournisseurRepository {

    private final JdbcTemplate jdbcTemplate;

    public FournisseurRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 8️⃣ Fournisseurs français (nom, contact, ville) triés par ville
    public List<Map<String, Object>> findFrenchSuppliers() {
        return jdbcTemplate.queryForList("SELECT Societe AS NomFournisseur, Contact, Ville FROM Fournisseur" +
                "WHERE Pays='France' ORDER BY Ville ASC");
    }

    public List<Map<String, Object>> countProduitsParFournisseur() {
        return jdbcTemplate.queryForList("SELECT f.NumFourn, f.NomFourn, COUNT(p.RefProd) AS NbProduits" +
                "FROM Fournisseur f LEFT JOIN Produit p ON p.NumFourn = f.NumFourn GROUP BY f.NumFourn, f.NomFourn");
    }

    public List<Map<String, Object>> countProduitsParPays() {
        return jdbcTemplate.queryForList("SELECT f.Pays, COUNT(p.RefProd) AS NbProduits FROM Fournisseur f" +
                "LEFT JOIN Produit p ON f.NumFourn = p.NumFourn GROUP BY f.Pays");
    }

    // 2. Nombre de produits proposés par "Mayumis"
    public Integer countProductsByMayumis() {
        return jdbcTemplate.queryForObject("SELECT (" +
                "SELECT COUNT(*) FROM Produit p WHERE p.NumFourn = f.NumFourn) AS NbProduits" +
                "FROM Fournisseur f WHERE f.NomFourn = 'Mayumis'", Integer.class);
    }

    // 2. Fournisseurs dont aucun produit n'a été commandé par un client français
    public List<Map<String, Object>> getSuppliersWithoutFrenchClientsOrders() {
        return jdbcTemplate.queryForList("SELECT * FROM Fournisseur f WHERE NOT EXISTS (" +
                "SELECT 1 FROM Produit p WHERE p.NumFourn = f.NumFourn AND p.RefProd IN (" +
                "SELECT dc.RefProd FROM DetailCommande dc WHERE dc.NoCom IN (" +
                "SELECT c.NoCom FROM Commande c WHERE c.CodeCli IN (" +
                "SELECT cli.CodeCli FROM Client cli WHERE cli.Pays = 'France'))))");
    }

    // 4. Nombre de fournisseurs n’ayant pas de commandes livrées au Canada
    public Integer countSuppliersWithoutCanadaOrders() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Fournisseur f WHERE NOT EXISTS (" +
                "SELECT 1 FROM Produit p WHERE p.NumFourn = f.NumFourn AND p.RefProd IN (" +
                "SELECT dc.RefProd FROM DetailCommande dc WHERE dc.NoCom IN (" +
                "SELECT c.NoCom FROM Commande c WHERE c.PaysLiv = 'Canada')))", Integer.class);
    }
}