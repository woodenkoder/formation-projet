package com.nocturne.lanterne.commande;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/** Testé avec H2… qui n'est pas PostgreSQL : ce test passe, mais que prouve-t-il pour la prod ? */
@DataJpaTest
class CommandeRepositoryTest {

    @Autowired
    CommandeRepository commandes;

    @Test
    void enregistrer_et_relire() {
        commandes.save(new Commande("CMD-1", "ada", LocalDateTime.now(), new BigDecimal("12.50")));
        assertThat(commandes.findTop10ByClientOrderByDateCommandeDesc("ada")).hasSize(1);
    }
}
