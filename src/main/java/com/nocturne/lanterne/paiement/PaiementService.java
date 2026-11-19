package com.nocturne.lanterne.paiement;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;

/** L'API publique du domaine paiement : au-delà de 1000 €, la banque refuse. */
@Service
public class PaiementService {

    static final BigDecimal PLAFOND = new BigDecimal("1000.00");

    private final PaiementRepository paiements;

    public PaiementService(PaiementRepository paiements) {
        this.paiements = paiements;
    }

    public ResultatPaiement payer(long commandeId, BigDecimal montant) {
        boolean accepte = montant.compareTo(PLAFOND) <= 0;
        Paiement paiement = paiements.enregistrer(new Paiement(null, commandeId, montant, accepte));
        return new ResultatPaiement(paiement.id(), accepte);
    }
}
