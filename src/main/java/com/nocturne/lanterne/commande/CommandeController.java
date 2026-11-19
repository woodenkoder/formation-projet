package com.nocturne.lanterne.commande;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** L'API des commandes. Elle marche… mais elle fait tout elle-même. */
@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    private final CommandeRepository repository;
    private final Catalogue catalogue;

    public CommandeController(CommandeRepository repository, Catalogue catalogue) {
        this.repository = repository;
        this.catalogue = catalogue;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommandeCreee creer(@RequestBody NouvelleCommande commande) {
        // Le total : prix × quantité de chaque ligne.
        BigDecimal total = BigDecimal.ZERO;
        for (NouvelleCommande.Ligne ligne : commande.lignes()) {
            total = total.add(catalogue.prix(ligne.produitId()).multiply(BigDecimal.valueOf(ligne.quantite())));
        }
        // Remise fidélité : -10 % à partir de la 4e commande du même client.
        if (repository.compterParClient(commande.client()) >= 3) {
            total = total.multiply(new BigDecimal("0.90"));
        }
        total = total.setScale(2, RoundingMode.HALF_UP);

        Commande enregistree = repository.enregistrer(new Commande(null, commande.client(), total, "EN_ATTENTE"));
        return new CommandeCreee(enregistree.id(), enregistree.statut(), enregistree.total());
    }
}
