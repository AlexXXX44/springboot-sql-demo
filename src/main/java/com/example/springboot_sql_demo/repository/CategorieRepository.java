package com.example.springboot_sql_demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CategorieRepository {

    private JdbcTemplate jdbcTemplate;

    public void CategorieRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    // 1️⃣ Lister uniquement la description des catégories
    public List<Map<String, Object>> findAllDescriptions() {return jdbcTemplate.queryForList("SELECT description FROM Categorie");}

}