package com.nocturne.lanterne.commande;

import java.math.BigDecimal;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CommandeService {

    private final Map<Long, Commande> commandes = Map.of(
            1L, new Commande(1L, "ada@exemple.test", new BigDecimal("50.00"), "COLISSIMO", new BigDecimal("4.90"),
                    "EN_ATTENTE"),
            2L, new Commande(2L, "linus@exemple.test", new BigDecimal("125.00"), "RETRAIT", new BigDecimal("0.00"),
                    "EXPEDIEE"));

    public Commande trouver(Long id) {
        Commande commande = commandes.get(id);
        if (commande == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Commande introuvable : " + id);
        }
        return commande;
    }
}
