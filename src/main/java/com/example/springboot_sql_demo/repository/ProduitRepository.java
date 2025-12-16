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
        return jdbcTemplate.queryForList("SELECT * FROM Produit ORDER BY PrixUnit DESC LIMIT 3");
    }

    // 5️⃣ Produits vendus en bouteilles ou en canettes
    public List<Map<String, Object>> findBottlesOrCans() {
        return jdbcTemplate.queryForList("SELECT * FROM Produit WHERE QteParUnit LIKE '%bouteille%' OR QteParUnit LIKE '%canette%'");
    }

    // 7️⃣ Produits du fournisseur n°8 entre 10 et 100 euros
    public List<Map<String, Object>> findProductsFromSupplier8Between10And100() {
        return jdbcTemplate.queryForList("SELECT UPPER(Nomprod) AS NomProduitMaj, Refprod AS Reference, PrixUnit AS PrixUnitaire" +
                "FROM Produit WHERE noFour = 8 AND PrixUnit BETWEEN 10 AND 100");
    }

    // 6️⃣ Produits contenant 'tofu' ou 'choco' et prix < 100
    public List<Map<String, Object>> findTofuOrChocoUnder100() {
        return jdbcTemplate.queryForList("SELECT * FROM Produit WHERE (Nomprod LIKE '%tofu' OR Nomprod LIKE '%choco%') AND PrixUnit<200");
    }

    public List<Map<String, Object>> findDisponibiliteProduits() {
        return jdbcTemplate.queryForList("SELECT RefProd, NomProd, Indisponible," +
                "CASE WHEN Indisponible=1 THEN 'Produit non disponible'" +
                "ELSE 'Produit disponible' END AS Disponibilite FROM Produit");
    }

    public Integer countProduitsMoins50() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) AS NbProduitsMoins50 FROM Produit WHERE PrixUnit < 50", Integer.class);
    }

    public int countProduitsCat2Stock10() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) AS nbProduitsCat2Stock10 FROM Produit WHERE CodeCateg = 2 AND UnitesStock > 10", Integer.class);
    }

    public int countProduitsCat1Four1ou18() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) AS NbProduitsCat1Four1ou18 FROM Produit WHERE CodeCateg = 1 AND NoFour IN (1,18)", Integer.class);
    }

    public List<Map<String, Object>> countCategoriesParFournisseur() {
        return jdbcTemplate.queryForList("SELECT NoFour AS NumFournisseur, COUNT(DISTINCT CodeCateg) AS NbCategoriesFournies FROM Produit" +
                "GROUP BY NoFour ORDER BY NoFour");
    }

    public List<Map<String, Object>> fournisseursUnSeulProduit() {
        return jdbcTemplate.queryForList("SELECT NoFour AS NumeroFournisseur, Societe AS NomFournisseur, COUNT(*) AS NbProduits" +
                "FROM Produit JOIN Fournisseur USING (NoFour) GROUP BY NoFour, Societe HAVING COUNT(*)=1 ORDER BY Societe");
    }

    public List<Map<String, Object>> categoriesPrixMoyenSup150(){
        return jdbcTemplate.queryForList("SELECT CodeCateg AS CodeCategorie, NomCateg AS NomCategorie, ROUND(AVG(PrixUnit),2) AS PrixMoyen"
                +"FROM Produit JOIN Categorie USING (CodeCateg) GROUP BY CodeCateg, NomCateg HAVING AVG(PrixUnit) > 150"
                +"ORDER BY PrixMoyen DESC");
    }

    public List<Map<String, Object>> fournisseursUneCategorie() {
        return jdbcTemplate.queryForList("SELECT NoFour AS NumeroFournisseur, Societe AS NomFournisseur, COUNT(DISTINCT CodeCateg) AS NbCategories" +
                "FROM Produit JOIN Fournisseur USING (NoFour) GROUP BY NoFour, Societe" +
                "HAVING COUNT(DISTINCT CodeCateg) = 1 ORDER BY Societe");
    }

    public List<Map<String, Object>> categoriesAvecPrixMoyenEtMinProduits() {
        return jdbcTemplate.queryForList("SELECT c.CodeCateg AS CodeCategorie, c.NomCateg AS NomCategorie," +
                "COUNT(p.RefProd) AS NbProduits, ROUND(AVG(p.PrixUnit),2) AS MontantPrixMoyen FROM Produit p" +
                "JOIN Categorie c ON p.CodeCateg = c.CodeCateg GROUP BY c.CodeCateg, c.NomCateg" +
                "HAVING COUNT(p.RefProd) >= 10 ORDER BY MontantPrixMoyen DESC");
    }

    public List<Map<String,Object>> getProduitsAvecFournisseurs() {
        return jdbcTemplate.queryForList("SELECT p.RefProd AS ReferenceProduit, p.Nomprod AS NomProduit, p.PrixUnit AS PrixUnitaire," +
                "f.NoFour AS NumeroFournisseur, f.Societe AS NomFournisseur, f.Ville AS VilleFournisseur, f.Pays AS PaysFournisseur" +
                "FROM Produit p JOIN Fournisseur f ON p.NoFour= f.NoFour ORDER BY f.Societe, p.Nomprod");
    }

    public List<Map<String, Object>> countCommandesParProduit() {
        return jdbcTemplate.queryForList("SELECT p.RefProd, p.Nomprod, COUNT(dc.NoCom) AS NbCommandes FROM Produit p" +
                "LEFT JOIN DetailCommande dc ON p.RefProd = dc.RefProd GROUP BY p.RefProd, p.Nomprod" +
                "ORDER BY NbCommandes DESC");
    }

    public List<Map<String,Object>> getProduitsSansCommandes() {
        return jdbcTemplate.queryForList("SELECT p.RefProd, p.Nomprod, p.PrixUnit FROM Produit p" +
                "LEFT JOIN DetailCommande dc ON p.RefProd = dc.RefProd WHERE p.RefProd IS NULL");
    }

    public List<Map<String, Object>> getProduitsAvecFournisseursFaitMain() {
        return jdbcTemplate.queryForList("SELECT" +
                "    p.RefProd," +
                "    p.Nomprod," +
                "    p.PrixUnit," +
                "    p.NoFour," +
                "    (SELECT f.NoFour" +
                "     FROM Fournisseur f" +
                "     WHERE f.NoFour = p.NoFour) AS NomFournisseur," +
                "    (SELECT f.Ville" +
                "     FROM Fournisseur f" +
                "     WHERE f.NoFour = p.NoFour) AS VilleFournisseur," +
                "    (SELECT f.Pays" +
                "     FROM Fournisseur f" +
                "     WHERE f.NoFour = p.NoFour) AS PaysFournisseur" +
                "FROM Produit p");
    }
}