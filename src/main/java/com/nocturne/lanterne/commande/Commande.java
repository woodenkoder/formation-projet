package com.nocturne.lanterne.commande;

import java.math.BigDecimal;

/** Une commande et son propriétaire (le nom de son compte). */
public record Commande(Long id, String client, BigDecimal montant) {}
