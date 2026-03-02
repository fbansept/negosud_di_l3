package edu.ban7.negosud_di_l3.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.negosud_di_l3.view.CommandeView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(CommandeView.class)
    protected Integer id;

    LocalDateTime date = LocalDateTime.now();

    @OneToMany(mappedBy = "commande")
    @JsonView(CommandeView.class)
    List<LigneCommande> ligneCommandes = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('PANIER', 'A_VALIDER', 'VALIDEE', 'ANNULEE', 'REMBOURSEE')" )
    protected StatusCommande status = StatusCommande.PANIER;

    @ManyToOne(optional = false)
    protected Utilisateur utilisateur;

}
