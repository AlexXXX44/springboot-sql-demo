@Repository
public FournisseurRepository {

    private final JdbcTemplate jdbcTemplate;

    public FournisseurRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    // 8️⃣ Fournisseurs français (nom, contact, ville) triés par ville
    public List<Map<String, Object>> findFrenchSuppliers() {
        return jdbcTemplate.queryForList("SELECT Societe AS NomFournisseur, Contact, Ville FROM Fournisseur" +
                "WHERE Pays='France' ORDER BY Ville ASC");
    }
}