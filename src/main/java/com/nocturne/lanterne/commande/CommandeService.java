package com.nocturne.lanterne.commande;

import com.nocturne.lanterne.catalogue.Catalogue;
import com.nocturne.lanterne.paiement.Paiement;
import com.nocturne.lanterne.paiement.PaiementRepository;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/** Les commandes… qui vont fouiller directement dans les entrailles du paiement. */
@Service
public class CommandeService {

    private final AtomicLong sequence = new AtomicLong();
    private final Catalogue catalogue;
    private final PaiementRepository paiements;

    public CommandeService(Catalogue catalogue, PaiementRepository paiements) {
        this.catalogue = catalogue;
        this.paiements = paiements;
    }

    public Commande commander(String client, BigDecimal montant) {
        if (!catalogue.peutCommander(client)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Client bloqué");
        }
        long id = sequence.incrementAndGet();
        // La règle de paiement recopiée ici, et l'enregistrement fait à la place du domaine paiement.
        boolean accepte = montant.compareTo(new BigDecimal("1000.00")) <= 0;
        paiements.enregistrer(new Paiement(null, id, montant, accepte));
        return new Commande(id, client, montant, accepte ? "PAYEE" : "PAIEMENT_REFUSE");
    }
}
