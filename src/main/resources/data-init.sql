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

/* password  = root */
INSERT INTO utilisateur (email, password, role) VALUES
      ('a@a.com', '$2a$10$RZv3E2.J/si3VsBQHbFkE.DnO39daxY7J7lv/6ZzBovEDxCzuE6OW', 'ADMIN'),
      ('b@b.com', '$2a$10$RZv3E2.J/si3VsBQHbFkE.DnO39daxY7J7lv/6ZzBovEDxCzuE6OW', 'CLIENT'),
      ('c@c.com', '$2a$10$RZv3E2.J/si3VsBQHbFkE.DnO39daxY7J7lv/6ZzBovEDxCzuE6OW', 'EMPLOYE');


INSERT INTO commande (date, status, utilisateur_id) VALUES
          (NOW(), 'PANIER', 1) ,
          ('2025-10-26 08:35:14.000000', 'PANIER', 2),
          ('2025-10-26 08:34:14.000000', 'ANNULEE', 2);

INSERT INTO ligne_commande (commande_id, produit_id , quantite, prix_total) VALUES
         (1, 2, 6, 75),
         (1, 3, 2, 20.4),
         (2, 2, 1, 10.4);




