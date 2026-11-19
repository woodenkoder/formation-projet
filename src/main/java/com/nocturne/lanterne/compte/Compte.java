package com.nocturne.lanterne.compte;

/** Un compte d'administration de la Lanterne. */
public record Compte(Long id, String email, String nom, String role, String empreinte) {

    public Compte avecEmpreinte(String nouvelle) {
        return new Compte(id, email, nom, role, nouvelle);
    }
}
