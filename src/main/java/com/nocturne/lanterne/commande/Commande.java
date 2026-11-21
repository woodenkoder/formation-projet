package com.nocturne.lanterne.commande;

import java.math.BigDecimal;

/** Une commande d'un client (identifié par son e-mail). */
public record Commande(long id, String client, String article, BigDecimal montant, String statut) {}
