INSERT INTO famille (designation) VALUES
        ('Vin blanc'),
        ('Vin rouge');


INSERT INTO produit (nom, prix, famille_id) VALUES
        ('Reisling', 12.5, 1),
        ('Coteau du Layon', 8.5, 1),
        ('Haut Medoc', 10.2, 2);

INSERT INTO fournisseur (nom) VALUES
          ('Fournisseur 1'),
          ('Fournisseur 2');

INSERT INTO produit_fournisseur (fournisseur_id, produit_id, prix_achat) VALUES
      (1, 1, 10),
      (1, 3, 10.8),
      (2, 2 , 5),
      (2, 3, 12);