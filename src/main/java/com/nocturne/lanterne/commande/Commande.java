package com.nocturne.lanterne.commande;

import java.math.BigDecimal;

public record Commande(long id, String client, BigDecimal montant, String statut) {}
