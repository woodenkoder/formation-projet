package com.nocturne.lanterne.commande;

import java.math.BigDecimal;

/** Une commande, côté métier : montant hors taxes et livraison. */
public record Commande(Long id, String client, BigDecimal montantHt, String modeLivraison, BigDecimal prixLivraison,
        String statut) {

    static final BigDecimal TVA = new BigDecimal("1.20");
}
