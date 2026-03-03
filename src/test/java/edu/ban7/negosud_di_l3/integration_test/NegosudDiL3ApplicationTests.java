package edu.ban7.negosud_di_l3.integration_test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
class NegosudDiL3ApplicationTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void callProduitList_shouldReturn200() throws Exception {
        mvc.perform(get("/api/produit/list"))
                .andExpect(status().isOk());
    }

    @Test
    void callVendorList_shouldReturn403() throws Exception {
        mvc.perform(get("/api/fournisseur/list"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"EMPLOYE"})
    void callVendorListAsEmploye_shouldReturn200() throws Exception {
        mvc.perform(get("/api/fournisseur/list"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = "a@a.com") // nécessaire si on utilise @AuthenticationPrincipal
    void callPanierAsClient_shouldReturn200() throws Exception {
        mvc.perform(get("/api/commande/panier"))
                .andExpect(status().isOk());
    }

}
