package com.nocturne.lanterne.commande;

public class CommandeIntrouvableException extends RuntimeException {

    public CommandeIntrouvableException(Long id) {
        super("Commande introuvable : " + id);
    }
}
