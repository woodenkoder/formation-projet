package com.nocturne.lanterne.commande;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Les règles des commandes : total, remise fidélité, enregistrement. */
@Service
public class CommandeService {

    @Autowired
    private CommandeRepository repository;

    @Autowired
    private Catalogue catalogue;

    @Autowired
    private FideliteService fidelite;

    public CommandeCreee creer(NouvelleCommande commande) {
        BigDecimal total = BigDecimal.ZERO;
        for (NouvelleCommande.Ligne ligne : commande.lignes()) {
            total = total.add(catalogue.prix(ligne.produitId()).multiply(BigDecimal.valueOf(ligne.quantite())));
        }
        total = fidelite.appliquer(commande.client(), total).setScale(2, RoundingMode.HALF_UP);

        Commande enregistree = repository.enregistrer(new Commande(null, commande.client(), total, "EN_ATTENTE"));
        return new CommandeCreee(enregistree.id(), enregistree.statut(), enregistree.total());
    }
}
