package edu.ban7.negosud_di_l3.unit_test;

import edu.ban7.negosud_di_l3.TestUtils;
import edu.ban7.negosud_di_l3.model.Produit;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProduitTest {

    static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory =
                Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    void setPriceCloseToRoundedUpperUnit_shouldRoundPriceCloseToRoundedUpperUnit() {
        Produit produit = new Produit();
        produit.setPrix(5.219f);
        Assertions.assertEquals(5.22f, produit.getPrix());
    }

    @Test
    void setPriceCloseToRoundedLowerUnit_shouldRoundPriceCloseToRoundedLowerUnit() {
        Produit produit = new Produit();
        produit.setPrix(5.214f);
        Assertions.assertEquals(5.21f, produit.getPrix());
    }

    @Test
    void setPriceEqualToBalancedRoundedUnit_shouldRoundPriceCloseToRoundedUpperUnit() {
        Produit produit = new Produit();
        produit.setPrix(5.215f);
        Assertions.assertEquals(5.22f, produit.getPrix());
    }

    @Test
    void createProduitWithBlankName_shouldBeInvalid() {
        Produit produit = new Produit();
        produit.setNom("");

        Assertions.assertTrue(TestUtils.constraintExist(
                validator.validate(produit),
                "nom",
                "NotBlank"));
    }

    @Test
    void createProduitWithNegativePrice_shouldBeInvalid() {
        Produit produit = new Produit();
        produit.setPrix(-0.01f);

        Assertions.assertTrue(TestUtils.constraintExist(
                validator.validate(produit),
                "prix",
                "DecimalMin"));
    }

    @Test
    void createProduitWithPriceEqual0_shouldBeInvalid() {
        Produit produit = new Produit();
        produit.setPrix(0);

        Assertions.assertTrue(TestUtils.constraintExist(
                validator.validate(produit),
                "prix",
                "DecimalMin"));
    }

    @Test
    void createProduitWithPositivePrice_shouldBeValid() {
        Produit produit = new Produit();
        produit.setPrix(0.0099f);

        Assertions.assertFalse(TestUtils.constraintExist(
                validator.validate(produit),
                "prix",
                "DecimalMin"));
    }

}
