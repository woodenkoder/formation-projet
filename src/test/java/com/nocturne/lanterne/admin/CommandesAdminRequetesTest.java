package com.nocturne.lanterne.admin;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/** Ne modifie pas ce test : il compte les requêtes SQL envoyées par l'écran d'administration. */
@SpringBootTest(properties = "spring.jpa.properties.hibernate.generate_statistics=true")
@AutoConfigureMockMvc
class CommandesAdminRequetesTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ClientRepository clients;

    @Autowired
    CommandeRepository commandes;

    @Autowired
    EntityManagerFactory emf;

    @Test
    void cent_commandes_en_deux_requetes_au_plus() throws Exception {
        LocalDate jour = LocalDate.of(2026, 1, 15);
        for (int i = 0; i < 100; i++) {
            Client client = clients.save(new Client("Client " + i, "client" + i + "@exemple.test"));
            commandes.save(new Commande(client, jour, new BigDecimal("10.00")));
        }
        Statistics stats = emf.unwrap(SessionFactory.class).getStatistics();
        stats.clear();

        mvc.perform(get("/api/admin/commandes").param("date", "2026-01-15"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(100));

        assertThat(stats.getPrepareStatementCount()).as("requêtes SQL pour 100 commandes").isLessThanOrEqualTo(2);
    }
}
