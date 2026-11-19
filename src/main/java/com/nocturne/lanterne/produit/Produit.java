package com.nocturne.lanterne.produit;

import java.math.BigDecimal;

/** Un produit du catalogue de Nocturne. */
public record Produit(Long id, String nom, BigDecimal prix) {}
