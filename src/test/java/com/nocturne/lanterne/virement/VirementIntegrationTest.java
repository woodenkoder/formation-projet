package com.nocturne.lanterne.virement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/** Ne modifie pas ce test : c'est lui qui prouve le bug. */
@SpringBootTest
class VirementIntegrationTest {

    @Autowired
    VirementService service;

    @Autowired
    CompteRepository comptes;

    @Autowired
    MouvementRepository mouvements;

    @BeforeEach
    void viderLaBase() {
        mouvements.deleteAll();
        comptes.deleteAll();
    }

    @Test
    void un_virement_refuse_ne_laisse_aucune_trace() {
        Compte ada = comptes.save(new Compte("Ada", new BigDecimal("5000.00")));
        Compte linus = comptes.save(new Compte("Linus", new BigDecimal("0.00")));

        assertThatThrownBy(() -> service.virer(ada.getId(), linus.getId(), new BigDecimal("1500.00")))
                .isInstanceOf(PlafondDepasseException.class);

        assertThat(mouvements.count()).as("le débit a été enregistré sans le crédit").isZero();
        assertThat(comptes.findById(ada.getId()).orElseThrow().getSolde()).isEqualByComparingTo("5000.00");
    }
}
