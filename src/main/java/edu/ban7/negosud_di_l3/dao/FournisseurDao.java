package edu.ban7.negosud_di_l3.dao;


import edu.ban7.negosud_di_l3.model.Famille;
import edu.ban7.negosud_di_l3.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FournisseurDao extends JpaRepository<Fournisseur, Integer> {


}
