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
}