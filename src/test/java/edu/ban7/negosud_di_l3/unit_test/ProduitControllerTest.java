package edu.ban7.negosud_di_l3.unit_test;

import edu.ban7.negosud_di_l3.controller.ProduitController;
import edu.ban7.negosud_di_l3.dao.ProduitDao;
import edu.ban7.negosud_di_l3.mock.MockProduitDao;
import edu.ban7.negosud_di_l3.model.Produit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ProduitControllerTest {

    @Test
    void getListProducts_shouldResponseWithCode200() {
        ProduitController controller = new ProduitController(new MockProduitDao());
        ResponseEntity<List<Produit>> reponse = controller.list();
        Assertions.assertEquals(HttpStatus.OK, reponse.getStatusCode());
    }

    @Test
    void getNExistingProduct_shouldResponseWithCode200() {
        ProduitController controller = new ProduitController(new MockProduitDao());
        ResponseEntity<Produit> reponse = controller.get(1);
        Assertions.assertEquals(HttpStatus.OK, reponse.getStatusCode());
    }

    @Test
    void getNotExistingProduct_shouldResponseWithCode404() {
        ProduitController controller = new ProduitController(new MockProduitDao());
        ResponseEntity<Produit> reponse = controller.get(99);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, reponse.getStatusCode());
    }

}
