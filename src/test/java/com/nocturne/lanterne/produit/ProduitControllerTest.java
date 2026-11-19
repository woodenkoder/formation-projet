package com.nocturne.lanterne.produit;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProduitController.class)
class ProduitControllerTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    ProduitService service;

    @Test
    void lire_un_produit() throws Exception {
        when(service.trouver(1L)).thenReturn(Optional.of(new Produit(1L, "Lanterne de poche", new BigDecimal("12.50"))));
        mvc.perform(get("/api/produits/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Lanterne de poche"));
    }
}
