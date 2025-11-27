package edu.ban7.negosud_di_l3.controller;

import edu.ban7.negosud_di_l3.dao.LigneCommandeDao;
import edu.ban7.negosud_di_l3.model.LigneCommande;
import edu.ban7.negosud_di_l3.security.IsAdmin;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ligne-commande")
@IsAdmin
public class LigneCommandeController {

    protected final LigneCommandeDao ligneCommandeDao;

    @GetMapping("/list")
    public List<LigneCommande> list() {
        return ligneCommandeDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LigneCommande> get(@PathVariable int id) {

        Optional<LigneCommande> optionalLigneCommande =  ligneCommandeDao.findById(id);

        if(optionalLigneCommande.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalLigneCommande.get(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<LigneCommande> create(@RequestBody LigneCommande ligneCommande) {

        ligneCommandeDao.save(ligneCommande);

        return new ResponseEntity<>(ligneCommande, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<LigneCommande> delete(@PathVariable int id) {

        Optional<LigneCommande> optionalLigneCommande =  ligneCommandeDao.findById(id);

        if(optionalLigneCommande.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ligneCommandeDao.deleteById(id);

        return new ResponseEntity<>(optionalLigneCommande.get(), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<LigneCommande> update(
            @PathVariable int id,
            @RequestBody LigneCommande ligneCommande) {

        //par sécurité, on remplace l'id du json par l'id de l'url
        ligneCommande.setId(id);

        //est ce que le ligneCommande existe bien en bdd ?
        Optional<LigneCommande> optionalLigneCommande =  ligneCommandeDao.findById(id);

        if(optionalLigneCommande.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ligneCommandeDao.save(ligneCommande);

        return new ResponseEntity<>(ligneCommande, HttpStatus.OK);
    }

}
