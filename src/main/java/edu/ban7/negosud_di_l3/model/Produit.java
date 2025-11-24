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
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(nullable = false, unique = true, length = 100)
    protected String nom;

    protected float prix;

    @ManyToOne(optional = false)
    protected Famille famille;

    @ManyToMany
    @JoinTable(
            name= "etiquette_produit",
            joinColumns = @JoinColumn(name = "produit_id"),
            inverseJoinColumns = @JoinColumn(name = "etiquette_id")
    )
    protected List<Etiquette> etiquettes = new ArrayList<>();
}
