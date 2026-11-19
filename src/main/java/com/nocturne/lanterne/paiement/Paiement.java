package com.nocturne.lanterne.paiement;

import java.math.BigDecimal;

/** Un paiement enregistré : un détail interne au domaine paiement. */
public record Paiement(Long id, long commandeId, BigDecimal montant, boolean accepte) {}
