package edu.ban7.negosud_di_l3.controller;

import edu.ban7.negosud_di_l3.dao.UtilisateurDao;
import edu.ban7.negosud_di_l3.model.Utilisateur;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    protected final UtilisateurDao utilisateurDao;
    protected final PasswordEncoder passwordEncoder;
    protected final AuthenticationProvider authenticationProvider;

    /**
     * Ajoute un utilisateur en base de donnée
     * @param utilisateur
     * @return
     */
    @PostMapping("/inscription")
    public ResponseEntity<String> signIn(@RequestBody Utilisateur utilisateur) {

        //TODO : valider les données (verifier sure l'email a un format d'email ...)

        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        utilisateurDao.save(utilisateur);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /**
     * retourne un JWT
     * @param utilisateur
     * @return
     */
    @PostMapping("/connexion")
    public ResponseEntity<String> login(@RequestBody Utilisateur utilisateur) {

        try {

            authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(utilisateur.getEmail(), utilisateur.getPassword()));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String jwt = Jwts
                .builder()
                .setSubject(utilisateur.getEmail())
                .signWith(SignatureAlgorithm.HS512, "azerty")
                .compact();

        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

}
