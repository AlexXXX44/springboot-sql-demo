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
        return jdbcTemplate.queryForList("SELECT DISTINCT FROM Client ORDER BY Pays ASC, Ville ASC");
    }

    public List<Map<String, Object>> findClientsNomInclutContact() {
        return jdbcTemplate.queryForList("SELECT CodeCli, Societe, Contact, Fonction FROM Client WHERE Societe LIKE CONCAT ('%', Contact, '%')");
    }

    public List<Map<String, Object>> findClientsWithFormattedData() {
        return jdbcTemplate.queryForList("SELECT CONCAT(Adresse, ', ', CodePostal, ' ', Ville, ', ', Pays) AS 'Adresse complète'"
                + "RIGHT(CodeCli, 2) AS 'DerniersCaractères', LOWER(Societe) AS 'SocieteMinuscule'"
                + "REPLACE(Fonction, 'marketing', 'mercatique') AS 'FonctionCorrigee',"
                + "CASE WHEN Fonction LIKE '%Chef%' THEN 'Oui' ELSE 'Non' END AS 'ContientChef'"
                + "FROM Client");
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
}