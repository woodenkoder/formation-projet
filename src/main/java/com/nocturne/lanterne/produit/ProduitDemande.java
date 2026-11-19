package com.nocturne.lanterne.produit;

import java.math.BigDecimal;

/** Ce que le client envoie pour créer ou modifier un produit. */
public record ProduitDemande(String nom, BigDecimal prix) {}
