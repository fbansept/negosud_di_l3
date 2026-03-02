package edu.ban7.negosud_di_l3.model;

import edu.ban7.negosud_di_l3.security.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(nullable = false)
    protected String email;

    @Column(nullable = false)
    protected String password;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('CLIENT', 'ADMIN', 'EMPLOYE')")
    protected Role role;

    public interface onCreation {}
    public interface onMiseAjour {}
}
