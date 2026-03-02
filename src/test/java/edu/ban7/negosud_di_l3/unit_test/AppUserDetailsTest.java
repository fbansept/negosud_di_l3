package edu.ban7.negosud_di_l3.unit_test;

import edu.ban7.negosud_di_l3.model.Utilisateur;
import edu.ban7.negosud_di_l3.security.AppUserDetails;
import edu.ban7.negosud_di_l3.security.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppUserDetailsTest {

    @Test
    void createAppUserDetailsWithAdminUser_shouldHaveGrantedAuthorityEqualToROLE_ADMIN() {
        //initialisé l'objet du contexte
        AppUserDetails appUserDetails = new AppUserDetails(
                new Utilisateur(1, "a@a.com", "root", Role.ADMIN));

        //tester si l'objet contient bien l'information demandée
        Assertions.assertEquals("ROLE_ADMIN",
                appUserDetails.getAuthorities().toArray()[0].toString());
    }

}
