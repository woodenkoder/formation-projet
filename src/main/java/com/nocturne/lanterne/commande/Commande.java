package com.nocturne.lanterne.commande;

import java.math.BigDecimal;

/** Une commande : son client, son montant et son statut. */
public record Commande(Long id, String client, BigDecimal montant, String statut) {

    public Commande avecStatut(String nouveau) {
        return new Commande(id, client, montant, nouveau);
    }
}
