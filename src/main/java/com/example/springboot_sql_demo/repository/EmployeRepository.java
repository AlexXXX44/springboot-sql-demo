package com.example.springboot_sql_demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeRepository {

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
}