package com.example.springboot_sql_demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class EmployeRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public EmployeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 9️⃣ Employés ayant réalisé une commande à livrer en France (Lille, Lyon, Nantes)
    public List<Map<String, Object>> findEmployeesWithFrenchOrders() {
        return jdbcTemplate.queryForList("SELECT DISTINCT NoEmp FROM Commande WHERE PaysLiv = 'France' AND VilleLiv IN ('Lille', 'Lyon', 'Nantes')");
    }

    public List<Map<String, Object>> findInfosEmbauche() {
//        return jdbcTemplate.queryForList("SELECT NoEmp, Nom, prenom, EXTRACT(YEAR FROM DateNaiss) AS AnneeNaissance," +
//                "EXTRACT(YEAR FROM DateEmbauche) AS AnneeEmbauche FROM Employe");
        return List.of();
    }

    public List<Map<String, Object>> findInfosEmbauche1() {
//        return jdbcTemplate.queryForList("SELECT EXTRACT(YEAR FROM DateEmbauche) - EXTRACT(YEAR FROM DateNaiss AS AgeEmbauche," +
//                "EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM DateEmbauche) AS AncienneAnnée FROM Employe");
        return List.of();
    }

    public int countRepresentants() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) AS NbRepresentants FROM Employe WHERE Fonction LIKE '%représentant%'", Integer.class);
    }

   public List<Map<String, Object>> countEmployesParFonction() {
        return jdbcTemplate.queryForList("SELECT Fonction, COUNT(*) AS Employes FROM Employe GROUP BY Fonction ORDER BY Fonction");
    }

    public List<Map<String, Object>> fonctionsAgeMoyenSup45() {
        return jdbcTemplate.queryForList("SELECT Fonction, ROUND(AVG(" +
                "                       (strftime('%Y', 'now') - strftime('%Y', DateNaissance))" +
                "                       - (strftime('%m-%d', 'now') < strftime('%m-%d', DateNaissance)),1) AS AgeMoyen " +
                "FROM Employe GROUP BY Fonction HAVING AgeMoyen > 45 " +
                "ORDER BY AgeMoyen DESC)");
    }

    public List<Map<String, Object>> employeMoinsDeCommandes() {
        return jdbcTemplate.queryForList("SELECT e.NoEmp AS NumeroEmploye, e.Nom AS NomEmploye," +
                "COUNT(c.NoCom) AS NbCommandes FROM Employe e LEFT JOIN Commande c ON e.NoEmp = c.NoEmp " +
                "GROUP BY e.NoEmp, e.Nom ORDER BY NbCommandes ASC LIMIT 1");
    }

    // 4️⃣ Responsable de chaque employé (auto-jointure)
    public List<Map<String, Object>> getEmployesAvecResponsable() {
        return jdbcTemplate.queryForList("SELECT e.NoEmp AS NumeroEmploye, e.Nom AS NomEmploye, e.Prenom AS PrenomEmploye," +
                "s.Nom AS NomEmploye, s.prenom AS PrenomResponsable FROM Employe e " +
                "INNER JOIN Employe s ON e.NoEmp = s.NoEmp WHERE Fonction='Responsable' ORDER BY e.NoEmp");
    }

    //    Existe-t'il un employé n'ayant enregistré aucune commande ?
    public List<Map<String, Object>> getEmployesSansCommande() {
        return jdbcTemplate.queryForList("SELECT e.NoEmp, e.Nom, e.prenom FROM Employe e LEFT JOIN Commande c ON e.NoEmp = c.NoEmp " +
                "WHERE c.NoEmp IS NULL");
    }

    public List<Map<String, Object>> countCommandesParEmploye() {
        return jdbcTemplate.queryForList("SELECT e.NoEmp, e.Nom, e.Prenom, COUNT(c.NoCom) AS NbCommandes FROM Employe e " +
                "LEFT JOIN Commande c ON e.NoEmp = c.NoEmp GROUP BY e.NoEmp, e.Nom, e.Prenom");
    }

    public List<Map<String, Object>> countPaysClientsParEmploye() {
        return jdbcTemplate.queryForList("SELECT e.NoEmp, e.Nom, e.Prenom, COUNT(DISTINCT cli.Pays) AS NbPaysClients " +
                "FROM Employe e LEFT JOIN Commande c ON c.NoEmp = e.NoEmp" +
                "               LEFT JOIN Client cli ON c.CodeCli = cli.CodeCli " +
                "GROUP BY e.NoEmp, e.Nom, e.Prenom");
    }

    // 1. Employés n'ayant jamais effectué une commande
    public List<Map<String, Object>> getEmployeesWithoutOrders() {
        return jdbcTemplate.queryForList("SELECT * FROM Employe e WHERE e.NoEmp NOT IN (" +
                "SELECT c.NoEmp FROM Commande c)");
    }

    // 5. Employés ayant une clientèle dans tous les pays
    public List<Map<String, Object>> getEmployeesWithClientsInAllCountries() {
        return jdbcTemplate.queryForList("SELECT e.* FROM Employe e WHERE NOT EXISTS (" +
                "SELECT cli.Pays FROM Client cli GROUP BY cli.Pays HAVING cli.Pays NOT IN (" +
                "SELECT DISTINCT cli2.Pays FROM Client cli2 WHERE cli2.CodeCli IN (" +
                "SELECT c.CodeCli FROM Commande c WHERE c.NoEmp = e.NoEmp)))");
    }
}