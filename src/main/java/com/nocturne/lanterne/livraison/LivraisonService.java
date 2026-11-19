package com.nocturne.lanterne.livraison;

import org.springframework.stereotype.Service;

@Service
public class LivraisonService {

    private final TransporteurClient transporteur;

    public LivraisonService(TransporteurClient transporteur) {
        this.transporteur = transporteur;
    }

    /** Le délai annoncé au client pendant la validation de sa commande. */
    public String delai(String codePostal) {
        return "livraison sous " + transporteur.delaiJours(codePostal) + " jours";
    }
}
