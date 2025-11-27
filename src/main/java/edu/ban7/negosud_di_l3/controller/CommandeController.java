package edu.ban7.negosud_di_l3.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.negosud_di_l3.dao.CommandeDao;
import edu.ban7.negosud_di_l3.model.Commande;
import edu.ban7.negosud_di_l3.model.StatusCommande;
import edu.ban7.negosud_di_l3.model.Utilisateur;
import edu.ban7.negosud_di_l3.security.IsEmploye;
import edu.ban7.negosud_di_l3.view.CommandeView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/commande")
@IsEmploye
public class CommandeController {

    protected final CommandeDao commandeDao;

    @GetMapping("/list")
    @JsonView(CommandeView.class)
    public List<Commande> list() {
        return commandeDao.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(CommandeView.class)
    public ResponseEntity<Commande> get(@PathVariable int id) {

        Optional<Commande> optionalCommande =  commandeDao.findById(id);

        if(optionalCommande.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalCommande.get(), HttpStatus.OK);

    }

    @GetMapping("/annulees")
    @JsonView(CommandeView.class)
    public List<Commande> listeCommandeAnnulees() {
        return commandeDao.findByStatus(StatusCommande.ANNULEE);
    }

    @GetMapping("/panier")
    @JsonView(CommandeView.class)
    public ResponseEntity<Commande> getPanier() {

        //Test en attendant le système d'authentification
        Utilisateur fauxUtilisateurConnecte = new Utilisateur(1,null,null,null);

        Optional<Commande> optionalPanier =  commandeDao.getPanier(fauxUtilisateurConnecte);

        if(optionalPanier.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalPanier.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Commande> create(@RequestBody Commande commande) {

        commandeDao.save(commande);

        return new ResponseEntity<>(commande, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Commande> delete(@PathVariable int id) {

        Optional<Commande> optionalCommande =  commandeDao.findById(id);

        if(optionalCommande.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        commandeDao.deleteById(id);

        return new ResponseEntity<>(optionalCommande.get(), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Commande> update(
            @PathVariable int id,
            @RequestBody Commande commande) {

        //par sécurité, on remplace l'id du json par l'id de l'url
        commande.setId(id);

        //est ce que le commande existe bien en bdd ?
        Optional<Commande> optionalCommande =  commandeDao.findById(id);

        if(optionalCommande.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        commandeDao.save(commande);

        return new ResponseEntity<>(commande, HttpStatus.OK);
    }

}
