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
//        return jdbcTemplate.queryForList("SELECT DAYNAME(DateCom) AS JourSemaine, WEEK(DateCom) AS NumeroSemaine, MONTHNAME(DateCom) AS Mois FROM Commande");
        return List.of();
    }

    public List<Map<String, Object>> findCommandesAvecDates2() {
//        return jdbcTemplate.queryForList("SELECT DAYNAME(DateCom) AS JourSemaine FROM Commande WHERE DAYOFWEEK(DateCom)=1");
        return List.of();
    }

    public List<Map<String, Object>> findCommandesAvecDates3() {
//        return jdbcTemplate.queryForList("SELECT DATEDIFF(ALivAvant, DateCom) AS JoursEntreCommandeEtLivraison FROM Commande");
        return List.of();
    }

    public List<Map<String, Object>> findCommandesAvecDates4() {
        return jdbcTemplate.queryForList("SELECT DATE_ADD(DateCom, INTERVAL 1 YEAR) AS Relance_1_an," +
                "SELECT DATE_ADD(DateCom, INTERVAL 1 MONTH) AS Relance_1_mois" +
                "DATE_ADD(DateCom, INTERVAL 1 WEEK) AS Relance_1_Semaine FROM Commande"
        );
    }

    public List<Map<String, Object>> findStatutLivraison() {
        return jdbcTemplate.queryForList("SELECT NoCom, DateCom, ALivAvant, DateEnv," +
                "CASE WHEN DateEnv >= ALivAvant THEN 'Commande envoyée en retard'" +
                "ELSE 'Commande envoyée à temps' END AS StatutLivraison" +
                "FROM Commande WHERE DateEnv IS NOT NULL");
    }

    public List<Map<String, Object>> findCommandesEnRetard() {
        return jdbcTemplate.queryForList("SELECT NumCom, DateCom, ALivAvant, DateEnv, (DateEnv - DateCom) AS DelaiEnvoi," +
                "CASE WHEN DateEnv > ALivAvant THEN (DateEnv - ALivAvant) ELSE 0 END AS JoursRetard" +
                "FROM Commande WHERE DateEnv > ALivAvant");
    }

    public int countPaysLivraison() {
        return jdbcTemplate.queryForObject("SELECT COUNT(DISTINCT PaysLiv) AS NbPaysLivraison FROM Commande", Integer.class);
    }

    public int countCommandes280316() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) AS NbCommandes280316 FROM Commande WHERE DateCom = '2016-03-28'", Integer.class);
    }

    public List<Map<String, Object>> top5ClientsParCommandes() {
        return jdbcTemplate.queryForList("SELECT c.CodeCli AS CodeClient, cli.Societe AS NomClient, COUNT(*) AS NbCommandes" +
                "FROM Commande c JOIN Client cli ON C.CodeCli = cli.CodeCli GROUP BY c.CodeCli, cli.Societe ORDER BY NbCommandes DESC LIMIT 5");
    }

    public List<Map<String, Object>> getCommandesLazyK() {
        return jdbcTemplate.queryForList("SELECT * FROM Commande c WHERE c.CodeCli=(" +
                "SELECT cli.CodeCli FROM Client Cli WHERE cli.Societe = 'LAZY K Kountry Store')");
    }

    public List<Map<String, Object>> nbCommandesParMessager() {
        return jdbcTemplate.queryForList("SELECT m.NoMess as messager_id, m.NomMess as nom_messager, COUNT(*) as nb_commandes" +
                "FROM Commande c JOIN Messager m ON c.noMess = m.NoMess GROUP BY m.NoMess, m.NomMess" +
                "ORDER BY nb_commandes DESC");
    }

    public List<Map<String, Object>> getCommandesClientLazyK() {
        return jdbcTemplate.queryForList("SELECT c.NoCom AS NumCommande, c.DateCom AS DateCommande, c.ALivAvant AS DateLimiteLivraison," +
                "c.DateEnv AS DateEnvoi, c.PaysLiv AS PaysLivraison, c.Ville AS VilleLivraison, c.Port AS FraisDePort, cli.Societe AS NomClient" +
                "FROM Commande c INNER JOIN Client cli ON c.CodeCli = cli.CodeCli WHERE cli.Societe='Lazy K Kountry Store' ORDER BY c.DateCom");
    }

//    public List<Map<String, Object>> countCommandesParMessager() {
//        return jdbcTemplate.queryForList("SELECT m.NoMess AS NumMessager, m.NomMess AS NomMessager, COUNT(c.NoCom) AS NbCommandes" +
//                "FROM Commande c INNER JOIN Messager m ON c.NoMess = m.NoMess GROUP BY m.NoMess, m.NomMess ORDER BY NbCommandes DESC");
//    }

    public List<Map<String, Object>> countCommandesParMessagerFaitMain() {
        return jdbcTemplate.queryForList("SELECT m.NoMess, m.NomMess, (" +
                "SELECT COUNT(*) FROM Commande c WHERE c.NoMess = m.NoMess) AS NbCommandes" +
                "FROM Messager m")
                ;
    }

    // 3. Nombre de commandes des employés sous "Patrick Emery"
    public Integer countOrdersByEmployeesUnderPatrickEmery() {

        return jdbcTemplate.queryForObject("SELECT COUNT(*)" +
                "FROM Commande c" +
                "WHERE c.NoEmp IN (" +
                "SELECT e.NoEmp FROM Employe e WHERE e.NoResp = (" +
                "SELECT emp.NoEmp FROM Employe emp" +
                "WHERE emp.NomEmp = 'Emery' AND emp.PrenomEmp ='Patrick' " +
                ")", Integer.class
        );
    }

}
