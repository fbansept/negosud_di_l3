package edu.ban7.negosud_di_l3.dao;


import edu.ban7.negosud_di_l3.model.Famille;
import edu.ban7.negosud_di_l3.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilleDao extends JpaRepository<Famille, Integer> {


}
