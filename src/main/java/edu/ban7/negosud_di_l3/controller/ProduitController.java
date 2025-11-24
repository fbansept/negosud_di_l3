package edu.ban7.negosud_di_l3.controller;

import edu.ban7.negosud_di_l3.dao.ProduitDao;
import edu.ban7.negosud_di_l3.model.Produit;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/produit")
public class ProduitController {

    protected final ProduitDao produitDao;

    @GetMapping("/list")
    public List<Produit> list() {
        return produitDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> get(@PathVariable int id) {

        Optional<Produit> optionalProduit =  produitDao.findById(id);

        if(optionalProduit.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalProduit.get(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Produit> create(@RequestBody Produit produit) {

        produitDao.save(produit);

        return new ResponseEntity<>(produit, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Produit> delete(@PathVariable int id) {

        Optional<Produit> optionalProduit =  produitDao.findById(id);

        if(optionalProduit.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        produitDao.deleteById(id);

        return new ResponseEntity<>(optionalProduit.get(), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> update(
            @PathVariable int id,
            @RequestBody Produit produit) {

        //par sécurité, on remplace l'id du json par l'id de l'url
        produit.setId(id);

        //est ce que le produit existe bien en bdd ?
        Optional<Produit> optionalProduit =  produitDao.findById(id);

        if(optionalProduit.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        produitDao.save(produit);

        return new ResponseEntity<>(produit, HttpStatus.OK);
    }

}
