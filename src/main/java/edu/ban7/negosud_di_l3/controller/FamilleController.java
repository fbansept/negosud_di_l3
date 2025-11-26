package edu.ban7.negosud_di_l3.controller;

import edu.ban7.negosud_di_l3.dao.FamilleDao;
import edu.ban7.negosud_di_l3.model.Famille;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/famille")
public class FamilleController {

    protected final FamilleDao familleDao;

    @GetMapping("/list")
    public List<Famille> list() {
        return familleDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Famille> get(@PathVariable int id) {

        Optional<Famille> optionalFamille =  familleDao.findById(id);

        if(optionalFamille.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalFamille.get(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Famille> create(@RequestBody Famille famille) {

        familleDao.save(famille);

        return new ResponseEntity<>(famille, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Famille> delete(@PathVariable int id) {

        Optional<Famille> optionalFamille =  familleDao.findById(id);

        if(optionalFamille.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        familleDao.deleteById(id);

        return new ResponseEntity<>(optionalFamille.get(), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Famille> update(
            @PathVariable int id,
            @RequestBody Famille famille) {

        //par sécurité, on remplace l'id du json par l'id de l'url
        famille.setId(id);

        //est ce que le famille existe bien en bdd ?
        Optional<Famille> optionalFamille =  familleDao.findById(id);

        if(optionalFamille.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        familleDao.save(famille);

        return new ResponseEntity<>(famille, HttpStatus.OK);
    }

}
