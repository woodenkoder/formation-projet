package com.nocturne.lanterne.commande;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class FideliteServiceTest {

    @MockitoBean
    CommandeRepository repository;

    @Autowired
    FideliteService fidelite;

    @Test
    void pas_de_remise_avant_la_quatrieme_commande() {
        when(repository.compterParClient("ada@exemple.test")).thenReturn(2L);
        assertThat(fidelite.appliquer("ada@exemple.test", new BigDecimal("30.00"))).isEqualByComparingTo("30.00");
    }

    @Test
    void remise_a_partir_de_la_quatrieme_commande() {
        when(repository.compterParClient("ada@exemple.test")).thenReturn(3L);
        assertThat(fidelite.appliquer("ada@exemple.test", new BigDecimal("30.00"))).isEqualByComparingTo("27.00");
    }
}
