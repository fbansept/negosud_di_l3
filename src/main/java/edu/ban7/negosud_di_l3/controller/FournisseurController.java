package edu.ban7.negosud_di_l3.controller;

import edu.ban7.negosud_di_l3.dao.FournisseurDao;
import edu.ban7.negosud_di_l3.model.Fournisseur;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fournisseur")
public class FournisseurController {

    protected final FournisseurDao fournisseurDao;

    @GetMapping("/list")
    public List<Fournisseur> list() {
        return fournisseurDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fournisseur> get(@PathVariable int id) {

        Optional<Fournisseur> optionalFournisseur =  fournisseurDao.findById(id);

        if(optionalFournisseur.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalFournisseur.get(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Fournisseur> create(@RequestBody Fournisseur fournisseur) {

        fournisseurDao.save(fournisseur);

        return new ResponseEntity<>(fournisseur, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Fournisseur> delete(@PathVariable int id) {

        Optional<Fournisseur> optionalFournisseur =  fournisseurDao.findById(id);

        if(optionalFournisseur.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        fournisseurDao.deleteById(id);

        return new ResponseEntity<>(optionalFournisseur.get(), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Fournisseur> update(
            @PathVariable int id,
            @RequestBody Fournisseur fournisseur) {

        //par sécurité, on remplace l'id du json par l'id de l'url
        fournisseur.setId(id);

        //est ce que le fournisseur existe bien en bdd ?
        Optional<Fournisseur> optionalFournisseur =  fournisseurDao.findById(id);

        if(optionalFournisseur.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        fournisseurDao.save(fournisseur);

        return new ResponseEntity<>(fournisseur, HttpStatus.OK);
    }

}
