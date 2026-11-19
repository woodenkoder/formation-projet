package com.nocturne.lanterne.paiement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/** Le client du service de paiement. Sa configuration est éparpillée en @Value. */
@Component
public class PaiementClient {

    private final String url;
    private final String cle;

    public PaiementClient(@Value("${paiement.url}") String url, @Value("${paiement.cle}") String cle) {
        this.url = url;
        this.cle = cle;
    }

    /** L'adresse appelée pour payer une commande (l'appel réel arrive plus tard). */
    public String adressePaiement(long commandeId) {
        return url + "/paiements?commande=" + commandeId;
    }

    boolean estConfigure() {
        return !url.isBlank() && !cle.isBlank();
    }
}
