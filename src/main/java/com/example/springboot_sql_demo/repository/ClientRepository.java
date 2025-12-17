package com.example.springboot_sql_demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ClientRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 1️⃣ Clients français installés à Paris
    public List<Map<String, Object>> findFrenchClientsInParis() {
        return jdbcTemplate.queryForList("SELECT * FROM Client WHERE Pays = 'France' AND Ville = 'Paris'");
    }

    // 2️⃣ Clients suisses, allemands et belges
    public List<Map<String, Object>> findSwissGermanBelgianClients() {
        return jdbcTemplate.queryForList("SELECT * FROM Client WHERE Pays IN ('Suisse','Allemagne','Belgique')");
    }

    // 3️⃣ Clients sans numéro de fax
    public List<Map<String, Object>> findClientsWithoutFax() {
        return jdbcTemplate.queryForList("SELECT * FROM Client WHERE Fax IS NULL OR Fax = ''");
    }

    // 4️⃣ Clients dont la société contient 'restaurant'
    public List<Map<String, Object>> findClientsWithRestaurantInName() {
        return jdbcTemplate.queryForList("SELECT * FROM Client WHERE Societe LIKE '%restaurant%'");
    }

    // 5️⃣ Lister les différents pays des clients
    public List<Map<String, Object>> findDistinctCountries() {
        return jdbcTemplate.queryForList("SELECT DISTINCT Pays FROM Client;");
    }

    // 6️⃣ Lister les pays et villes triés alphabétiquement
    public List<Map<String, Object>> findDistinctCountriesAndCities() {
        return jdbcTemplate.queryForList("SELECT DISTINCT Pays, Ville FROM Client ORDER BY Pays ASC, Ville ASC");
    }

    public List<Map<String, Object>> findClientsNomInclutContact() {
        return jdbcTemplate.queryForList("SELECT CodeCli, Societe, Contact, Fonction FROM Client WHERE Societe LIKE CONCAT ('%', Contact, '%')");
    }

    public List<Map<String, Object>> findClientsWithFormattedData() {
        return jdbcTemplate.queryForList("SELECT CONCAT(Adresse, ', ', CodePostal, ' ', Ville, ', ', Pays) AS `Adresse complète`,"
                + "LOWER(Societe) AS SocieteMinuscule,"
                + "REPLACE(Fonction, 'marketing', 'mercatique') AS FonctionCorrigee,"
                + "CASE "
                + "WHEN Fonction LIKE '%Chef%' THEN 'Oui' "
                + "ELSE 'Non' "
                + "END AS ContientChef " +
                "FROM Client");
    }

    public List<Map<String, Object>> countProduitsClientCategorie() {
        return jdbcTemplate.queryForList("SELECT cli.CodeCli, cli.Societe, cat.CodeCateg, cat.NomCateg," +
                "SUM(dc.Qte) AS TotalProduits FROM Client cli" +
                "JOIN Commande c ON cli.CodeCli = c.CodeCli" +
                "JOIN DetailCommande dc ON c.NoCom = dc.NoCom" +
                "JOIN Produit p ON dc.RefProd = p.RefProd" +
                "JOIN Categorie cat ON cat.CodeCateg = p.CodeCateg" +
                "GROUP BY cli.CodeCli, cli.Societe, cat.CodeCateg, cat.NomCateg" +
                "ORDER BY cli.CodeCli, cat.CodeCateg");
    }

    // 1. Clients ayant commandé du Camembert Pierrot
    public List<Map<String, Object>> getClientsWhoOrderedCamembertPierrot() {
        return jdbcTemplate.queryForList("SELECT DISTINCT cli.CodeCli, cli.Societe" +
                " FROM Client cli WHERE cli.CodeCli IN (" +
                "SELECT c.CodeCli FROM Commande c WHERE c.NoCom IN (" +
                "SELECT dc.NoCom FROM DetailCommande dc WHERE dc.RefProd = (" +
                "SELECT p.RefProd FROM Produit p WHERE p.NomProd = 'Camembert Pierrot')))");
    }

    // 3. Clients ayant commandé tous les produits du fournisseur Exotic Liquids
    public List<Map<String, Object>> getClientsWhoOrderedAllExoticProducts() {
        return jdbcTemplate.queryForList("SELECT" +
//                " cli.* FROM Client cli WHERE NOT EXISTS (" +
//                "SELECT p.RefProd FROM Produit p WHERE p.NoFour = (" +
//                "SELECT f.NoFour FROM Fournisseur f WHERE f.Societe = 'Exotic Liquids')" +
//                "AND p.RefProd NOT IN (" +
//                "SELECT dc.RefProd FROM DetailCommande dc WHERE dc.NoCom IN (" +
//                "SELECT c.NoCom FROM Commande c WHERE c.CodeCli = cli.CodeCli)))");
                " c.CodeCli," +
                "COUNT(DISTINCT p.RefProd) AS produits_commandes," +
                "(" +
                "                        SELECT COUNT(*)" +
                "        FROM Produit p2" +
                "        JOIN Fournisseur f2 ON f2.NoFour = p2.NoFour" +
                "        WHERE f2.Societe = 'Exotic Liquids'" +
                "    ) AS produits_exotic" +
                "        FROM Client c " +
                "JOIN Commande co ON co.CodeCli = c.CodeCli " +
                "JOIN DetailCommande dc ON dc.NoCom = co.NoCom " +
                "JOIN Produit p ON p.RefProd = dc.RefProd " +
                "JOIN Fournisseur f ON f.NoFour = p.NoFour " +
                "WHERE f.Societe = 'Exotic Liquids' " +
                "GROUP BY c.CodeCli;");
    }
}