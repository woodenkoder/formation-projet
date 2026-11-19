package com.nocturne.lanterne.paiement;

/** Ce que le domaine paiement répond aux autres domaines. */
public record ResultatPaiement(long paiementId, boolean accepte) {}
