package edu.ban7.negosud_di_l3.dao;


import edu.ban7.negosud_di_l3.model.Commande;
import edu.ban7.negosud_di_l3.model.Produit;
import edu.ban7.negosud_di_l3.model.StatusCommande;
import edu.ban7.negosud_di_l3.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommandeDao extends JpaRepository<Commande, Integer> {

    List<Commande> findByStatus (StatusCommande status);

    @Query(value = "FROM Commande c WHERE c.status = 'PANIER' AND c.utilisateur = :client")
    Optional<Commande> getPanier(@Param("client") Utilisateur utilisateur);

}
