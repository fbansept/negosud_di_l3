package edu.ban7.negosud_di_l3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.negosud_di_l3.view.CommandeView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LigneCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(CommandeView.class)
    protected Integer id;

    @ManyToOne(optional = false)
    @JsonView(CommandeView.class)
    protected Produit produit;

    @ManyToOne(optional = false)
    protected Commande commande;

    @JsonView(CommandeView.class)
    protected int quantite;

    @JsonView(CommandeView.class)
    protected float prixTotal;

}
