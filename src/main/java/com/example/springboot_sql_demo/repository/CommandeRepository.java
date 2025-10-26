package com.example.springboot_sql_demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CommandeRepository {

    private final JdbcTemplate jdbcTemplate;

    public CommandeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> findCommandesAvecDates1() {
        return jdbcTemplate.queryForList("SELECT DAYNAME(DateCom) AS JourSemaine, WEEK(DateCom) AS NumeroSemaine, MONTHNAME(DateCom) AS Mois FROM Commande");
    }

    public List<Map<String, Object>> findCommandesAvecDates2() {
        return jdbcTemplate.queryForList("SELECT DAYNAME(DateCom) AS JourSemaine FROM Commande WHERE DAYOFWEEK(DateCom)=1");
    }

    public List<Map<String, Object>> findCommandesAvecDates3() {
        return jdbcTemplate.queryForList("SELECT DATEDIFF(ALivAvant, DateCom) AS JoursEntreCommandeEtLivraison FROM Commande");
    }

    public List<Map<String, Object>> findCommandesAvecDates4() {
        return jdbcTemplate.queryForList("SELECT DATE_ADD(DateCom, INTERVAL 1 YEAR) AS Relance_1_an," +
                "SELECT DATE_ADD(DateCom, INTERVAL 1 MONTH) AS Relance_1_mois" +
                "DATE_ADD(DateCom, INTERVAL 1 WEEK) AS Relance_1_Semaine FROM Commande"
        );
    }

    public List<Map<String, Object>> findStatutLivraison() {
        return jdbcTemplate.queryForList("SELECT NumCom, DateCom, ALivAvant, DateEnv," +
                                    "CASE WHEN DateEnv >= ALivAvant THEN 'Commande envoyée en retard'" +
                                    "ELSE 'Commande envoyée à temps' END AS StatutLivraison" +
                                    "FROM Commande WHERE DateEnv IS NOT NULL");
    }

    public List<Map<String, Object>> findCommandesEnRetard() {
        return jdbcTemplate.queryForList("SELECT NumCom, DateCom, ALivAvant, DateEnv, (DateEnv - DateCom) AS DelaiEnvoi," +
                "CASE WHEN DateEnv > ALivAvant THEN (DateEnv - ALivAvant) ELSE 0 END AS JoursRetard" +
                "FROM Commande WHERE DateEnv > ALivAvant");
    }
}
