INSERT INTO Client (CodeCli, Societe, Contact, Fonction, Adresse, Ville, CodePostal, Pays, Telephone, Fax)
VALUES
    ('QUICK', 'Quick Restaurant', 'Jean Dupont', 'Chef marketing', '1 rue de Paris', 'Paris', '75001', 'France', '0102030405', NULL),
    ('LAZYK', 'Lazy K Kountry Store', 'Anne Martin', 'Directrice', 'Main Street', 'Seattle', '98101', 'USA', '555-1234', '555-9999');
INSERT INTO Client
(CodeCli, Societe, Contact, Fonction, Adresse, Ville, CodePostal, Pays, Telephone, Fax)
VALUES
    ('ALPES', 'Alpes Market', 'Jean Dupont', 'Acheteur', '12 rue du Lac', 'Genève', '1201', 'Suisse', '0102030405', NULL),

    ('BERLIN', 'Berlin Food GmbH', 'Hans Müller', 'Responsable achats', 'Unter den Linden 5', 'Berlin', '10117', 'Allemagne', '030123456', NULL),

    ('BRUX', 'Bruxelles Gourmet', 'Pierre Martin', 'Directeur', '8 avenue Louise', 'Bruxelles', '1050', 'Belgique', '021234567', NULL);
INSERT INTO Client
(CodeCli, Societe, Contact, Fonction, Adresse, Ville, CodePostal, Pays)
VALUES
    (
        'CHEF1',
        'Restaurant Chez Martin',
        'Martin',
        'Gérant',
        '10 rue de Paris',
        'Paris',
        '75001',
        'France'
    ),
    (
        'CHEF2',
        'Dupont Traiteur',
        'Dupont',
        'Responsable',
        '5 avenue Victor Hugo',
        'Lyon',
        '69000',
        'France'
    ),
    (
        'CHEF3',
        'Boucherie Paul',
        'Paul',
        'Artisan',
        '3 rue Centrale',
        'Lille',
        '59000',
        'France'
    );
INSERT INTO Produit
(RefProd, NomProd, PrixUnit, QteStock, Indisponible)
VALUES
    (1, 'Camembert Pierrot', 12.50, 100, 0),
    (2, 'Brie de Meaux', 10.00, 50, 0);
INSERT INTO Commande
(NumCom, CodeCli, DateCom)
VALUES
    (1001, 'CHEF1', '2024-01-10'),
    (1002, 'CHEF2', '2024-01-12');
INSERT INTO DetailCommande
(NumCom, RefProd, Qte, PrixUnit, Remise)
VALUES
    (1001, 1, 5, 12.50, 0.00),  -- Camembert Pierrot
    (1002, 2, 3, 10.00, 0.00);  -- Autre produit
INSERT INTO Fournisseur (NoFour, Societe, Pays)
VALUES
    (1, 'Exotic Liquids', 'Royaume-Uni'),
    (2, 'Fromagerie Pierrot', 'France');
INSERT INTO Produit (RefProd, NomProd, NoFour, PrixUnit)
VALUES
    (10, 'Chai', 1, 18.00),
    (11, 'Chang', 1, 19.00),
    (12, 'Aniseed Syrup', 1, 10.00);

INSERT INTO DetailCommande
(NoCom, RefProd, Qte, PrixUnit, Remise)
VALUES
    (2001, 10, 2, 18.00, 0),
    (2001, 11, 1, 19.00, 0),
    (2002, 12, 3, 10.00, 0),
    (2003, 10, 1, 18.00, 0);

