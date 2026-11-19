package com.nocturne.lanterne.commande;

public class AnnulationImpossibleException extends RuntimeException {

    public AnnulationImpossibleException(Long id, String statut) {
        super("La commande " + id + " ne peut plus être annulée : elle est " + statut + ".");
    }
}
