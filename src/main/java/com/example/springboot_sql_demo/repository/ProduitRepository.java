package com.example.springboot_sql_demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProduitRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProduitRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> findAll() {
        return jdbcTemplate.queryForList("SELECT * FROM Produit LIMIT 10");
    }

    public List<Map<String, Object>> findAllSortedByPrix() {
        return jdbcTemplate.queryForList("SELECT * FROM Produit ORDER BY PrixUnit");
    }

    public List<Map<String, Object>> findTop3MostExpensive() {
        return jdbcTemplate.queryForList("SELECT * FROM Produit ORDER BY PrixUnit");
    }

    // 5️⃣ Produits vendus en bouteilles ou en canettes
    public List<Map<String, Object>> findBottlesOrCans() {
        return jdbcTemplate.queryForList("SELECT * FROM Produit WHERE Conditionnement LIKE '%bouteille%' OR Conditionnement LIKE '%canette%'");
    }

    // 7️⃣ Produits du fournisseur n°8 entre 10 et 100 euros
    public List<Map<String, Object>> findProductsFromSupplier8Between10And100() {
        return jdbcTemplate.queryForList("SELECT UPPER(NomProduit) AS NomProduitMaj," +
                "RefProduit AS Reference," +
                "PrixUnit AS PrixUnitaire" +
                "FROM Produit WHERE noFour = 8 AND PrixUnit BETWEEN 10 AND 100");
    }

    // 6️⃣ Produits contenant 'tofu' ou 'choco' et prix < 100
    public List<Map<String, Object>> findTofuOrChocoUnder100() {
        return jdbcTemplate.queryForList("SELECT * FROM Produit WHERE (NomProduit LIKE '%tofu' OR NomProduit LIKE '%choco%') AND PrixUnit<200");
    }

    public List<Map<String, Object>> findDisponibiliteProduits() {
        return jdbcTemplate.queryForList("SELECT RefProd, NomProd, Indisponible," +
                "CASE WHEN Indisponible=1 THEN 'Produit non disponible'" +
                "ELSE 'Produit disponible' END AS Disponibilite FROM Produit");
    }

    public List<Map<String,Object>> getProduitsAvecFournisseurs() {
        return jdbcTemplate.queryForList("SELECT p.RefProd AS ReferenceProduit, p.NomProd AS NomProduit, p.PrixUnit AS PrixUnitaire," +
                "f.NumFourn AS NumeroFournisseur, f.NomFourn AS NomFournisseur, f.Ville AS VilleFournisseur, f.Pays AS PaysFournisseur" +
                "FROM Produit p JOIN Fournisseur f ON p.NumFourn = f.NumFourn ORDER BY f.NomFourn, p.NomProd");
    }

    public List<Map<String, Object>> countCommandesParProduit() {
        return jdbcTemplate.queryForList("SELECT p.RefProd, p.NomProd, COUNT(dc.NoCom) AS NbCommandes FROM Produit p" +
                "LEFT JOIN DetailCommande dc ON p.RefProd = dc.RefProd GROUP BY p.RefProd, p.NomProd" +
                "ORDER BY NbCommandes DESC");
    }

    public List<Map<String,Object>> getProduitsSansCommandes() {
        return jdbcTemplate.queryForList("SELECT p.RefProd, p.NomProd, p.PrixUnit FROM Produit p" +
                "LEFT JOIN DetailCommande dc ON p.RefProd = dc.RefProd WHERE p.RefProd IS NULL");
    }

    public List<Map<String, Object>> getProduitsAvecFournisseursFaitMain() {
        return jdbcTemplate.queryForList("SELECT" +
                "    p.RefProd," +
                "    p.NomProd," +
                "    p.PrixUnit," +
                "    p.NumFourn," +
                "    (SELECT f.NomFourn" +
                "     FROM Fournisseur f" +
                "     WHERE f.NumFourn = p.NumFourn) AS NomFournisseur," +
                "    (SELECT f.Ville" +
                "     FROM Fournisseur f" +
                "     WHERE f.NumFourn = p.NumFourn) AS VilleFournisseur," +
                "    (SELECT f.Pays" +
                "     FROM Fournisseur f" +
                "     WHERE f.NumFourn = p.NumFourn) AS PaysFournisseur" +
                "FROM Produit p");
    }
}