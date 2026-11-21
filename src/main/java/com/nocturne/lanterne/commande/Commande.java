package com.nocturne.lanterne.commande;

import java.math.BigDecimal;
import java.util.List;

/** Une commande, avec le message que le client a laissé au service client. */
public record Commande(long id, String client, List<String> lignes, BigDecimal montant, String messageClient) {}
