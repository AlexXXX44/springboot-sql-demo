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
}