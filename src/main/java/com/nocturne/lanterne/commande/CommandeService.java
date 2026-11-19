package com.nocturne.lanterne.commande;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

/** Les commandes, en mémoire. La 1 et la 3 sont en attente, la 2 est déjà expédiée. */
@Service
public class CommandeService {

    private final AtomicLong sequence = new AtomicLong(3);
    private final Map<Long, Commande> commandes = new ConcurrentHashMap<>(Map.of(
            1L, new Commande(1L, "ada@exemple.test", "EN_ATTENTE"),
            2L, new Commande(2L, "linus@exemple.test", "EXPEDIEE"),
            3L, new Commande(3L, "grace@exemple.test", "EN_ATTENTE")));

    public Commande trouver(Long id) {
        Commande commande = commandes.get(id);
        if (commande == null) {
            throw new CommandeIntrouvableException(id);
        }
        return commande;
    }

    public Commande creer(NouvelleCommande nouvelle) {
        long id = sequence.incrementAndGet();
        Commande commande = new Commande(id, nouvelle.client(), "EN_ATTENTE");
        commandes.put(id, commande);
        return commande;
    }

    public Commande annuler(Long id) {
        Commande commande = trouver(id);
        if (!commande.statut().equals("EN_ATTENTE")) {
            throw new AnnulationImpossibleException(id, commande.statut());
        }
        Commande annulee = new Commande(id, commande.client(), "ANNULEE");
        commandes.put(id, annulee);
        return annulee;
    }

    /** La facture vient d'un autre service… qui ne répond pas toujours. */
    public String facture(Long id) {
        trouver(id);
        throw new IllegalStateException("Connexion refusée par facturation.nocturne.internal:8443 (pool épuisé)");
    }
}
