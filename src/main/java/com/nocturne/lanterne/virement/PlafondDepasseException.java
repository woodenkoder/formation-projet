package com.nocturne.lanterne.virement;

import java.math.BigDecimal;

/** Un virement au-dessus du plafond. Une exception vérifiée : l'appelant doit la traiter. */
public class PlafondDepasseException extends Exception {

    public PlafondDepasseException(BigDecimal montant) {
        super("Virement de " + montant + " € refusé : le plafond est de " + VirementService.PLAFOND + " €.");
    }
}
