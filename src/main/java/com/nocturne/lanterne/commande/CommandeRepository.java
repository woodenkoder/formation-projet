package com.nocturne.lanterne.commande;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class CommandeRepository {

    private final Map<Long, Commande> commandes = Map.of(
            1L, new Commande(1L, "ada@exemple.test", List.of("2 × Lanterne de poche", "1 × Grimoire relié"),
                    new BigDecimal("55.00"), "Le grimoire est arrivé déchiré, je veux être remboursée."),
            2L, new Commande(2L, "linus@exemple.test", List.of("1 × Bougie du manoir"), new BigDecimal("4.90"),
                    "Merci, tout est parfait !"));

    public Optional<Commande> trouver(long id) {
        return Optional.ofNullable(commandes.get(id));
    }
}
