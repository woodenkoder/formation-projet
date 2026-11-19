package com.nocturne.lanterne.commande;

/** Une commande et son statut (EN_ATTENTE, EXPEDIEE, ANNULEE). */
public record Commande(Long id, String client, String statut) {}
