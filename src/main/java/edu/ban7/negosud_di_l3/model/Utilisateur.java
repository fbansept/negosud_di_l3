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
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(nullable = false, unique = true, length = 100)
    protected String email;

    @Column(nullable = false)
    protected String password;

}
