package com.nocturne.lanterne.virement;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Les virements entre cagnottes. Un virement doit se faire en entier… ou pas du tout. */
@Service
public class VirementService {

    static final BigDecimal PLAFOND = new BigDecimal("1000.00");

    private final CompteRepository comptes;
    private final MouvementRepository mouvements;

    public VirementService(CompteRepository comptes, MouvementRepository mouvements) {
        this.comptes = comptes;
        this.mouvements = mouvements;
    }

    public void virer(Long de, Long vers, BigDecimal montant) throws PlafondDepasseException {
        enregistrer(de, vers, montant);
    }

    @Transactional
    public void enregistrer(Long de, Long vers, BigDecimal montant) throws PlafondDepasseException {
        Compte source = comptes.findById(de).orElseThrow(() -> new CompteIntrouvableException(de));
        source.debiter(montant);
        comptes.save(source);
        mouvements.save(new Mouvement(de, montant.negate()));

        if (montant.compareTo(PLAFOND) > 0) {
            throw new PlafondDepasseException(montant);
        }

        Compte destination = comptes.findById(vers).orElseThrow(() -> new CompteIntrouvableException(vers));
        destination.crediter(montant);
        comptes.save(destination);
        mouvements.save(new Mouvement(vers, montant));
    }
}
