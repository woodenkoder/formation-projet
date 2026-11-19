package com.nocturne.lanterne.commande;

import java.math.BigDecimal;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CommandeService {

    static final BigDecimal PLAFOND = new BigDecimal("5000.00");

    private final CommandeRepository commandes;

    public CommandeService(CommandeRepository commandes) {
        this.commandes = commandes;
    }

    @Transactional
    public Commande creer(String client, BigDecimal montant) {
        return commandes.save(new Commande(client, montant));
    }

    /** Valide la commande. Au-delà du plafond, une exception annule toute la transaction. */
    @Transactional
    public Commande valider(Long id) {
        Commande commande = commandes.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        commande.valider();
        if (commande.getMontant().compareTo(PLAFOND) > 0) {
            throw new PlafondDepasseException(id);
        }
        return commande;
    }
}
