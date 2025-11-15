package com.example.springboot_sql_demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class StatistiquesRepository {

    private final JdbcTemplate jdbcTemplate;

    public StatistiquesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int countRepresentants() {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) AS NbRepresentants FROM Employe WHERE Fonction LIKE '%représetant%'", Integer.class);
    }

    public Integer countProduitsMoins50() {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) AS NbProduitsMoins50 FROM Produit WHERE PrixUnit < 50", Integer.class);
    }

    public int countProduitsCat2Stock10() {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) AS nbProduitsCat2Stock10 FROM Produit WHERE CodeCateg = 2 AND UnitesStock > 10", Integer.class);
    }

    public int countProduitsCat1Four1ou18() {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) AS NbProduitsCat1Four1ou18 FROM Produit WHERE CodeCateg = 1 AND NumFourn IN (1,18)", Integer.class);
    }

    public int countPaysLivraison() {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(DISTINCT PaysLiv) AS NbPaysLivraison FROM Commande", Integer.class);
    }

    public int countCommandes280316() {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) AS NbCommandes280316 FROM Commande WHERE DateCom = '2016-03-28'", Integer.class);
    }

    public double getCoutMoyenPortQuick() {
        return jdbcTemplate.queryForObject(
                "SELECT ROUND(AVG(Port)) AS CoutMoyenPort FROM Commande WHERE CodeCli ='QUICK'", Double.class);
    }

    public double getCoutPortMinMax() {
        return jdbcTemplate.queryForObject("SELECT MIN(Port) AS CoutPortMinimum, MAX(Port) AS CoutPortMaximum FROM Commande", Double.class);
    }

    public List<Map<String, Object>> getTotalFraisPortParMessager() {
        return jdbcTemplate.queryForList(
                "SELECT NoMess AS NumeroMessager, ROUND(SUM(Port),2) AS TotalFraisPort FROM Commande GROUP BY NoMess ORDER BY NoMess");
    }

    public List<Map<String, Object>> countEmployesParFonction() {
        return jdbcTemplate.queryForList("SELECT Fonction, COUNT(*) AS Employes, FROM Employe GROUP BY Fonction ORDER BY Fonction");
    }

    public List<Map<String, Object>> avgPortParMessager() {
        return jdbcTemplate.queryForList(
                "SELECT NoMess AS NumeroMessager, ROUND(AVG(Port), 2) AS MontantMoyenPort FROM Commande GROUP BY NoMess ORDER BY NoMess");
    }

    public List<Map<String, Object>> countCategoriesParFournisseur() {
        return jdbcTemplate.queryForList(
                "SELECT NumFourn AS NumFournisseur, COUNT(DISTINCT CodeCateg) AS NbCategoriesFournies FROM Produit GROUP BY NumFourn ORDER BY NumFour");
    }


    public List<Map<String, Object>> avgPrixParFournisseurEtCategorie() {
        return jdbcTemplate.queryForList("SELECT NumFourn AS NumeroFournisseur, CodeCateg AS CodeCategorie, ROUND(AVG(PrixUnit,2) AS PrixMoyen" +
                "FROM Produit GROUP BY NumFourn, CodeCateg ORDER BY NumFourn, CodeCateg");
    }

    public List<Map<String, Object>> fournisseursUnSeulProduit() {
        return jdbcTemplate.queryForList(
                "SELECT NumFourn AS NumeroFournisseur, NomFourn AS NomFournisseur, COUNT(*) AS NbProduits" +
                    "FROM Produit JOIN Fournisseur USING (NumFourn) GROUP BY NumFourn, NomFourn HAVING COUNT(*)=1 ORDER BY NomFourn");
    }

    public List<Map<String, Object>> categoriesPrixMoyenSup150(){
        return jdbcTemplate.queryForList("SELECT CodeCateg AS CodeCategorie, NomCateg AS NomCategorie, ROUND(AVG(PrixUnit),2) AS PrixMoyen"
                +"FROM Produit JOIN Categorie USING (CodeCateg) GROUP BY CodeCateg, NomCateg HAVING AVG(PrixUnit) > 150"
                +"ORDER BY PrixMoyen DESC");
    }


    public List<Map<String, Object>> fournisseursUneCategorie() {
        return jdbcTemplate.queryForList("SELECT NumFourn AS NumeroFournisseur, NomFourn AS NomFournisseur, COUNT(DISTINCT CodeCateg) AS NbCategories" +
                "FROM Produit JOIN Fournisseur USING (NumFourn) GROUP BY NumFourn, NomFourn" +
                "HAVING COUNT(DISTINCT CodeCateg) = 1 ORDER BY NomFourn");
    }

    public List<Map<String, Object>> fonctionsAgeMoyenSup45() {
        return jdbcTemplate.queryForList("SELECT Fonction, ROUND(AVG(TIMESTAMPDIFF(YEAR, Naissance, CURDATE())),1) AS AgeMoyen" +
                "FROM Employe GROUP BY Fonction HAVING AVG(TIMESTAMPDIFF(YEAR, Naissance, CURDATE())) > 45" +
                "ORDER BY AgeMoyen DESC");
    }

    public List<Map<String, Object>> quantiteTotaleParClientEtProduit() {
        return jdbcTemplate.queryForList("SELECT c.CodeCli AS CodeClient, cli.Societe AS NomClient," +
                "p.RefProd AS ReferenceProduit, p.NomProd AS NomProduit, SUM(dc.Qte) AS QuantiteTotale" +
                "FROM DetailCommande dc JOIN Commande c ON dc.NoCom = c.NoCom JOIN Client cli ON c.CodeCli = cli.codeCli" +
                "JOIN Produit p ON dc.RefProd = p.RefProd GROUP BY c.CodeCli, cli.Societe, p.RefProd, p.NomProd ORDER BY cli.Societe, p.NomProd");
    }

    public List<Map<String, Object>> top5ClientsParCommandes() {
        return jdbcTemplate.queryForList("SELECT c.CodeCli AS CodeClient, cli.Societe AS NomClient, COUNT(*) AS NbCommandes" +
                "FROM Commande c JOIN Client cli ON C.CodeCli = cli.CodeCli GROUP BY c.CodeCli, cli.Societe ORDER BY NbCommandes DESC LIMIT 5");
    }

    public List<Map<String, Object>> montantCommandesAvecEtSansRemise() {
        return jdbcTemplate.queryForList("SELECT dc.NoCom AS NumeroCommande," +
                "ROUND(SUM(dc.PrixUnit * dc.Qte), 2) AS MontantSansRemise" +
                "ROUND(SUM(dc.PrixUnit * dc.Qte * (1-dc.Remise)), 2) AS MontantAvecRemise FROM DetailCommande dc GROUP BY dc.NoCom ORDER BY dc.NoCom");
    }

    public List<Map<String, Object>> categoriesAvecPrixMoyenEtMinProduits() {
        return jdbcTemplate.queryForList("SELECT c.CodeCateg AS CodeCategorie, c.NomCateg AS NomCategorie" +
                "COUNT(p.RefProd) AS NbProduits, ROUND(AVG(p.PrixUnit),2) AS PrixMoyen FROM Produit p" +
                "JOIN Categorie c ON p.CodeCateg = c.CodeCateg GROUP BY c.CodeCateg, c.NomCateg" +
                "HAVING COUNT(p.RefProd) >= 10 ORDER BY PrixMoyen DESC");
    }

    public Map<String, Object> employeMoinsDeCommandes() {
        return (Map<String, Object>) jdbcTemplate.queryForList("SELECT e.NoEmp AS NumeroEmploye, e.NomEmp AS NomEmploye," +
                "COUNT(c.NoCom) AS NbCommandes FROM Employe e LEFT JOIN Commande ON e.NoEmp = c.NoEmp" +
                "GROUP BY e.NoEmp, e.NomEmp ORDER BY NbCommandes ASC LIMIT 1");
    }

    public List<Map<String, Object>> produitsEtFournisseurs() {
        return jdbcTemplate.queryForList("SELECT p.RefProd AS ReferenceProduit, p.NomProd AS NomProduit, p.PrixUnit AS PrixUnitaire," +
                "f.NumFourn AS NumeroFournisseur, f.NomFourn AS NomFournisseur, f.Ville AS VilleFournisseur" +
                "f.Pays AS PaysFournisseur FROM Produit p JOIN Fournisseur f ON p.NumFourn = f.NumFourn" +
                "ORDER BY f.NomFourn, p.NomProd");
    }

    public List<Map<String, Object>> commandesClientLazyK() {
        return jdbcTemplate.queryForList("SELECT c.NoCom AS NumeroCommande, c.DateCom AS DateCommande, c.ALivAvant AS DateLimiteLivraison," +
                "c.DateEnv AS DateEnvoi, c.PaysLiv AS PaysLivraison, c.Port AS FraisDePort, cli.Societe AS NomClient" +
                "FROM Commande c JOIN Client cli ON c.CodeCli = cli.CodeCli" +
                "WHERE cli.Societe = 'Lazy K Kountry Store' ORDER BY c.DateCom;");
    }

    public List<Map<String, Object>> nbCommandesParMessager() {
        return jdbcTemplate.queryForList("SELECT m.No_Mess as messager_id, m.NomMess as nom_messager, COUNT(*) as nb_commandes" +
                "FROM Commande c JOIN Messager m ON c.noMess = m.NoMess GROUP BY m.NoMess, m.NomMess" +
                "ORDER BY nb_commandes DESC");
    }
}