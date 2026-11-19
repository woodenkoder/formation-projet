package com.nocturne.lanterne.commande;

import jakarta.validation.constraints.NotBlank;

/** Ce que le client envoie pour passer une commande. */
public record NouvelleCommande(@NotBlank String client) {}
