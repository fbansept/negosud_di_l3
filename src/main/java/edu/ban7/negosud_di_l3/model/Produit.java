package edu.ban7.negosud_di_l3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.RoundingMode;
import java.text.DecimalFormat;

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
    @NotBlank
    protected String nom;

    @DecimalMin(value = "0.01")
    protected float prix;

    //arrondir au centime supérieur
    public void setPrix(float prix) {
         this.prix = (float) Math.round(prix * 100) / 100;
    }
}
