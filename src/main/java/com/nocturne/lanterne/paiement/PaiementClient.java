package com.nocturne.lanterne.paiement;

import org.springframework.stereotype.Component;

/** Le client du service de paiement. */
@Component
public class PaiementClient {

    private static final String URL = "https://paiement.nocturne.example/api";
    private static final String CLE_API = "pay-live-3c9f1e7a5b2d4c6e";

    /** L'en-tête d'autorisation envoyé au service de paiement. */
    public String autorisation() {
        return "Bearer " + CLE_API;
    }

    public String adresse(long commandeId) {
        return URL + "/paiements?commande=" + commandeId;
    }
}
