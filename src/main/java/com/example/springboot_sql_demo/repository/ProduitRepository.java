@Repository
public class ProduitRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProduitRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public findAal(){
        return jdbcTemplate.queryForList("SELECT * FROM Produit LIMIT 10");
    }

    public findAllSortedByPrix(){
        return jdbcTemplate.queryForList("SELECT * FROM Produit ORDER BY PrixUnit");
    }

    public findTop3MostExpensive(){
        return jdbcTemplate.queryForList("SELECT * FROM Produit ORDER BY PrixUnit");
    }

    // 5️⃣ Produits vendus en bouteilles ou en canettes
    public List<Map<String, Object>> findBottlesOrCans() {
        return jdbcTemplate.queryForList("SELECT * FROM Produit WHERE Conditionnement LIKE '%bouteille%' OR Conditionnement LIKE '%canette%'");
    }

    // 7️⃣ Produits du fournisseur n°8 entre 10 et 100 euros
    public List<Map<String, Object>> findProductsFromSupplier8Between10And100() {
        return jdbcTemplate.queryForList("SELECT UPPER(NomProduit) AS NomProduitMaj," +
                                        "RefProduit AS Reference," +
                                        "PrixUnit AS PrixUnitaire" +
                                        "FROM Produit WHERE noFour = 8 AND PrixUnit BETWEEN 10 AND 100");
    }

    // 6️⃣ Produits contenant 'tofu' ou 'choco' et prix < 100
    public List<Map<String, Object>> findTofuOrChocoUnder100() {
        return jdbcTemplate.queryForList("SELECT * FROM Produit WHERE (NomProduit LIKE '%tofu' OR NomProduit LIKE '%choco%') AND PrixUnit<200";
    }
}