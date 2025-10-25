@Repository
public ClientRepository {

    // 1️⃣ Clients français installés à Paris
    public List<Map<String, Object>> findFrenchClientsInParis () {
        return jdbcTemplate.queryForList("SELECT * FROM Client WHERE Pays = 'France' AND Ville = 'Paris'");
    }

    // 2️⃣ Clients suisses, allemands et belges
    public List<Map<String, Object>> findSwissGermanBelgianClients () {
        return jdbcTemplate.queryForList("SELECT * FROM Client WHERE Pays IN ('Suisse','Allemagne','Belgique')");
    }

    // 3️⃣ Clients sans numéro de fax
    public List<Map<String, Object>> findClientsWithoutFax () {
        return jdbcTemplate.queryForList("SELECT * FROM Client WHERE Fax IS NULL OR Fax = ''");
    }

    // 4️⃣ Clients dont la société contient 'restaurant'
    public List<Map<String, Object>> findClientsWithRestaurantInName () {
        return jdbcTemplate.queryForList("SELECT * FROM Client WHERE Societe LIKE '%restaurant%");
    }
}