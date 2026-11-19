package com.nocturne.lanterne.admin;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Une ligne de l'écran d'administration des commandes. */
public record CommandeAdmin(Long id, LocalDate date, BigDecimal montant, String client, String email) {}
