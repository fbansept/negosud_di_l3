INSERT INTO famille (designation) VALUES
        ('Vin blanc'),
        ('Vin rouge');

INSERT INTO fournisseur (nom) VALUES
      ('Fournisseur 1'),
      ('Fournisseur 2');

INSERT INTO produit (nom, prix, famille_id, fournisseur_id) VALUES
        ('Reisling', 12.5, 1, 1),
        ('Coteau du Layon', 8.5, 1, 2),
        ('Haut Medoc', 10.2, 2, 2);

INSERT INTO commande (date) VALUES
          (NOW()) , ('2025-10-26 08:34:14.000000');

INSERT INTO ligne_commande (commande_id, produit_id , quantite, prix_total) VALUES
         (1, 2, 6, 75),
         (1, 3, 2, 20.4),
         (2, 2, 1, 10.4);


