package com.nocturne.lanterne.remise;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

/** Les remises de Nocturne. La règle est dans REGLES-REMISES.md : c'est elle qu'il faut tester. */
@Service
public class RemiseService {

    static final BigDecimal SEUIL_BRONZE = new BigDecimal("50");
    static final BigDecimal SEUIL_ARGENT = new BigDecimal("100");
    static final int CLIENT_FIDELE = 5;
    static final String CODE_PROMO = "NOCTURNE";
    static final int PLAFOND = 20;
    private static final BigDecimal CENT = new BigDecimal("100");

    /** Le montant de la remise, en euros, arrondi au centime. */
    public BigDecimal remise(BigDecimal montant, int commandesPassees, String codePromo) {
        int taux = 0;
        if (montant.compareTo(SEUIL_BRONZE) >= 0) {
            taux = 5;
        }
        if (montant.compareTo(SEUIL_ARGENT) >= 0) {
            taux = 10;
        }
        if (commandesPassees >= CLIENT_FIDELE) {
            taux += 5;
        }
        if (CODE_PROMO.equals(codePromo)) {
            taux += 10;
        }
        taux = Math.min(taux, PLAFOND);
        return montant.multiply(BigDecimal.valueOf(taux)).divide(CENT, 2, RoundingMode.HALF_UP);
    }
}
