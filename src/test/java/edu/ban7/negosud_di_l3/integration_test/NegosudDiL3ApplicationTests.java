package edu.ban7.negosud_di_l3.integration_test;

import edu.ban7.negosud_di_l3.dao.ProduitDao;
import edu.ban7.negosud_di_l3.model.Produit;
import org.junit.jupiter.api.Assertions;
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
import tools.jackson.databind.json.JsonMapper;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
class NegosudDiL3ApplicationTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ProduitDao produitDao;

    private MockMvc mvc;

    private JsonMapper jsonMapper = JsonMapper.builder().build();

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
    @WithMockUser(roles = {"EMPLOYE"}) // possible si on n'utilise pas @AuthenticationPrincipal
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

    @Test
    @WithUserDetails(value = "a@a.com")
    void callProfil_shouldReturnOnlyNeededInformation() throws Exception {
        mvc.perform(get("/profil"))
                .andExpectAll(
                        jsonPath("$.password").doesNotExist(),
                        jsonPath("$.id").doesNotExist(),
                        jsonPath("$.email").exists(),
                        jsonPath("$.role").exists());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void createProductWithValidInformation_shouldReturn201AndProductIsCreated() throws Exception {

        //1ère solution : ecrire le JSON directement
        //String json = "{\"nom\":\"nouveau produit\", \"prix\" : 3.5}";

        //2ème solution, créer un objet en JAVA et le transformer en JSON
        Produit produit = new Produit(null, "nouveau produit", 3.5f);

        //note : Equivalent en JS à : JSON.stringify(...)
        String json = jsonMapper.writeValueAsString(produit);

        mvc.perform(post("/api/produit")
                    .contentType("application/json")
                    .content(json))
                .andExpect(status().isCreated())
                .andDo(result -> {
                    String jsonNouveauProduit = result.getResponse().getContentAsString();

                    //note : Equivalent en JS à : JSON.parse(...) mais on précise la classe
                    Produit nouveauProduit = jsonMapper.readValue(jsonNouveauProduit, Produit.class);

                    Assertions.assertNotNull(nouveauProduit.getId());

                    int dernierIdInsere = nouveauProduit.getId();

                    Assertions.assertTrue(produitDao.existsById(dernierIdInsere));
                });
    }

}
