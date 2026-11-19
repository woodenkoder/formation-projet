package com.nocturne.lanterne.virement;

/** Le compte demandé n'existe pas. */
public class CompteIntrouvableException extends RuntimeException {

    public CompteIntrouvableException(Long id) {
        super("Compte introuvable : " + id);
    }
}
