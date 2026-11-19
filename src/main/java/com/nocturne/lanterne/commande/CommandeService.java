package com.nocturne.lanterne.commande;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CommandeService {

    private final Map<Long, Commande> commandes = Map.of(
            1L, new Commande(1L, "ada", new BigDecimal("42.00")),
            2L, new Commande(2L, "linus", new BigDecimal("13.50")));

    public Optional<Commande> trouver(Long id) {
        return Optional.ofNullable(commandes.get(id));
    }

    public List<Commande> toutes() {
        return commandes.values().stream().sorted((a, b) -> a.id().compareTo(b.id())).toList();
    }
}
