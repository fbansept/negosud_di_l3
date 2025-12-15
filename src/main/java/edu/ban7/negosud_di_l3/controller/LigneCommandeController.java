package edu.ban7.negosud_di_l3.controller;

import edu.ban7.negosud_di_l3.dao.CommandeDao;
import edu.ban7.negosud_di_l3.dao.LigneCommandeDao;
import edu.ban7.negosud_di_l3.model.Commande;
import edu.ban7.negosud_di_l3.model.LigneCommande;
import edu.ban7.negosud_di_l3.model.StatusCommande;
import edu.ban7.negosud_di_l3.security.AppUserDetails;
import edu.ban7.negosud_di_l3.security.IsAdmin;
import edu.ban7.negosud_di_l3.security.IsClient;
import edu.ban7.negosud_di_l3.security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ligne-commande")
@IsClient
public class LigneCommandeController {

    protected final LigneCommandeDao ligneCommandeDao;
    protected final CommandeDao commandeDao;

    @GetMapping("/list")
    @IsAdmin
    public List<LigneCommande> list() {
        return ligneCommandeDao.findAll();
    }

    @GetMapping("/{id}")
    @IsAdmin
    public ResponseEntity<LigneCommande> get(@PathVariable int id) {

        Optional<LigneCommande> optionalLigneCommande =  ligneCommandeDao.findById(id);

        if(optionalLigneCommande.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalLigneCommande.get(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<LigneCommande> create(
            @RequestBody LigneCommande ligneCommande,
            @AuthenticationPrincipal AppUserDetails user) {

        Optional<Commande> optionalPanier =  commandeDao.getPanier(user.getUtilisateur());

        //si l'utilisateur n'a pas de panier (ce qui serait étrange) on lui en créait un
        if(optionalPanier.isEmpty()) {
            Commande panier = new Commande();
            panier.setUtilisateur(user.getUtilisateur());
            panier.setStatus(StatusCommande.PANIER);
            commandeDao.save(panier);

            ligneCommande.setCommande(panier);
        } else {
            ligneCommande.setCommande(optionalPanier.get());
        }

        ligneCommandeDao.save(ligneCommande);

        return new ResponseEntity<>(ligneCommande, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<LigneCommande> delete(
            @PathVariable int id,
            @AuthenticationPrincipal AppUserDetails user) {

        Optional<LigneCommande> optionalLigneCommande =  ligneCommandeDao.findById(id);

        if(optionalLigneCommande.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //On vérifie que l'utilisateur est propriétaire de la ligne de commande, ou qu'il est administrateur
        if(user.getUtilisateur().getRole() != Role.ADMIN
                &&  optionalLigneCommande.get().getCommande().getUtilisateur().getId() != user.getUtilisateur().getId()){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        ligneCommandeDao.deleteById(id);

        return new ResponseEntity<>(optionalLigneCommande.get(), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<LigneCommande> update(
            @PathVariable int id,
            @RequestBody LigneCommande ligneCommande,
            @AuthenticationPrincipal AppUserDetails user) {

        //par sécurité, on remplace l'id du json par l'id de l'url
        ligneCommande.setId(id);

        //est ce que le ligneCommande existe bien en bdd ?
        Optional<LigneCommande> optionalLigneCommande =  ligneCommandeDao.findById(id);

        if(optionalLigneCommande.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //On vérifie que l'utilisateur est propriétaire de la ligne de commande, ou qu'il est administrateur
        if(user.getUtilisateur().getRole() != Role.ADMIN
                &&  optionalLigneCommande.get().getCommande().getUtilisateur().getId() != user.getUtilisateur().getId()){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }


        ligneCommandeDao.save(ligneCommande);

        return new ResponseEntity<>(ligneCommande, HttpStatus.OK);
    }

}
