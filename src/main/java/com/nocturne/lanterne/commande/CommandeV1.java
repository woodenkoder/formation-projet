package com.nocturne.lanterne.commande;

import java.math.BigDecimal;

/** Le format v1 : celui que l'appli mobile lit aujourd'hui. */
public record CommandeV1(Long id, String client, BigDecimal montant, BigDecimal fraisPort, String statut) {

    static CommandeV1 de(Commande c) {
        return new CommandeV1(c.id(), c.client(), c.montantHt(), c.prixLivraison(), c.statut());
    }
}
