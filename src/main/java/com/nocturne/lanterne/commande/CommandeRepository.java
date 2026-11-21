package com.nocturne.lanterne.commande;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Repository;

/** Les commandes, en mémoire pour l'exercice. */
@Repository
public class CommandeRepository {

    private final List<Commande> commandes = List.of(
            new Commande(1, "ada@exemple.test", "Lanterne tempête", new BigDecimal("54.90"), "expédiée"),
            new Commande(2, "linus@exemple.test", "Grimoire de Linus", new BigDecimal("129.00"), "en préparation"),
            new Commande(3, "ada@exemple.test", "Mèches de rechange", new BigDecimal("9.90"), "livrée"));

    public List<Commande> duClient(String email) {
        return commandes.stream().filter(c -> c.client().equals(email)).toList();
    }
}
