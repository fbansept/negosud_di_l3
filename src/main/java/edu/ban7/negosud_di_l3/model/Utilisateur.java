package edu.ban7.negosud_di_l3.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.negosud_di_l3.security.Role;
import edu.ban7.negosud_di_l3.view.CommandeView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @NotBlank
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}")
    protected String email;

    @Column(nullable = false)
    @NotBlank
    protected String password;

    //protected boolean admin;


    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('CLIENT', 'ADMIN', 'EMPLOYE')")
    protected Role role;


}
