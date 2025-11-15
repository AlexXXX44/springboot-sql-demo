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
        String sql = "SELECT DISTINCT NoEmp FROM Commande WHERE PaysLiv = 'France' AND VilleLiv IN ('Lille', 'Lyon', 'Nantes')";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> findInfosEmbauche() {
        return jdbcTemplate.queryForList("SELECT NumEmp, NomEmp, PrenomEmp, EXTRACT(YEAR FROM DateNaiss) AS AnneeNaissance," +
                "EXTRACT(YEAR FROM DateEmbauche) AS AnneeEmbauche FROM Employe");
    }

    public List<Map<String, Object>> findInfosEmbauche1() {
        return jdbcTemplate.queryForList("SELECT EXTRACT(YEAR FROM DateEmbauche) - EXTRACT(YEAR FROM DateNaiss AS AgeEmbauche," +
                "EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM DateEmbauche) AS AncienneAnnée FROM Employe");
    }

    // 4️⃣ Responsable de chaque employé (auto-jointure)
    public List<Map<String, Object>> getEmployesAvecResponsable() {
        return jdbcTemplate.queryForList("SELECT e.NoEmp AS NumeroEmploye, e.NomEmp AS NomEmploye, e.PrenomEmp AS PrenomEmploye," +
                "s.NomEmp AS NomEmploye, s.PrenomEmp AS PrenomResponsable FROM Employe e" +
                "INNER JOIN Employe s ON e.NoEmp = s.NoEmp ORDER BY e.NoEmp");
    }

    //    Existe-t'il un employé n'ayant enregistré aucune commande ?
    public List<Map<String, Object>> getEmployesSansCommande() {
        return jdbcTemplate.queryForList("SELECT e.NoEmp, e.NomEmp, e.PrenomEmp FROM Employe e LEFT JOIN Commande c ON e.NoEmp = c.Emp" +
                "WHERE c.NoEmp IS NULL");
    }

    public List<Map<String, Object>> countCommandesParEmploye() {
        return jdbcTemplate.queryForList("SELECT e.NoEmp, e.NomEmp, e.PrenomEmp, COUNT(c.NoCom) AS NbCommandes FROM Employe e" +
                "LEFT JOIN Commande c ON e.NoEmp = c.NoEmp GROUP BY e.NoEmp, e.NomEmp, e.PrenomEmp");
    }

    public List<Map<String, Object>> countPaysClientsParEmploye() {
        return jdbcTemplate.queryForList("SELECT e.NoEmp, e.NomEmp, e.PrenomEmp, COUNT (DISTINCT cli.Pays) AS NbPaysClients" +
                "FROM Employe e LEFT JOIN Commande c ON c.NoEmp = e.NoEmp" +
                "               LEFT JOIN Client cli ON c.CodeCli = cli.CodeCli" +
                "GROUP BY e.NoEmp, e.NomEmp, e.PrenomEmp");
    }

    // 1. Employés n'ayant jamais effectué une commande
    public List<Map<String, Object>> getEmployeesWithoutOrders() {
        return jdbcTemplate.queryForList("SELECT * FROM Employe e WHERE e.NoEmp NOT IN (" +
                "SELECT c.NoEmp FROM Commande c)");
    }

    // 5. Employés ayant une clientèle dans tous les pays
    public List<Map<String, Object>> getEmployeesWithClientsInAllCountries() {
        return jdbcTemplate.queryForList("SELECT e.* FROM Employe e WHERE NOT EXISTS (" +
                "SELECT cli.Pays FROM Client Client Cli GROUP BY cli.Pays HAVING cli.Pays NOT IN (" +
                "SELECT DISTINCT cli2.Pays FROM Client cli2 WHERE cli2.CodeCli IN (" +
                "SELECT c.CodeCli FROM Commande c WHERE c.NoEmp = e.NoEmp)))");
    }
}