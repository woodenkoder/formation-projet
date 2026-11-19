package com.nocturne.lanterne.commande;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

/** Ces tests démarrent toute l'application pour tester une seule classe : lents, et Spring est obligatoire. */
@SpringBootTest
class CommandeServiceTest {

    @MockitoBean
    CommandeRepository repository;

    @MockitoBean
    FideliteService fidelite;

    @Autowired
    CommandeService service;

    @BeforeEach
    void preparer() {
        when(fidelite.appliquer(anyString(), any())).thenAnswer(appel -> appel.getArgument(1));
        when(repository.enregistrer(any())).thenAnswer(appel -> {
            Commande c = appel.getArgument(0);
            return new Commande(42L, c.client(), c.total(), c.statut());
        });
    }

    @Test
    void le_total_additionne_prix_fois_quantite() {
        NouvelleCommande commande = new NouvelleCommande("ada@exemple.test",
                List.of(new NouvelleCommande.Ligne(1L, 2), new NouvelleCommande.Ligne(2L, 1)), null);
        assertThat(service.creer(commande).total()).isEqualByComparingTo("55.00");
    }

    @Test
    void la_commande_creee_est_en_attente() {
        NouvelleCommande commande = new NouvelleCommande("ada@exemple.test",
                List.of(new NouvelleCommande.Ligne(3L, 1)), null);
        assertThat(service.creer(commande).statut()).isEqualTo("EN_ATTENTE");
    }

    @Test
    void la_remise_vient_du_service_fidelite() {
        when(fidelite.appliquer(anyString(), any())).thenReturn(new BigDecimal("1.00"));
        NouvelleCommande commande = new NouvelleCommande("ada@exemple.test",
                List.of(new NouvelleCommande.Ligne(2L, 1)), null);
        assertThat(service.creer(commande).total()).isEqualByComparingTo("1.00");
    }
}
