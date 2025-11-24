package edu.ban7.negosud_di_l3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProduitFournisseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @ManyToOne(optional = false)
    protected Produit produit;

    @ManyToOne(optional = false)
    protected Fournisseur fournisseur;

    protected float prixAchat;

}
